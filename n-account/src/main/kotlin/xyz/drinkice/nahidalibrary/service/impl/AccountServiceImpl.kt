package xyz.drinkice.nahidalibrary.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.drinkice.nahidalibrary.common.model.BizErrorTypeEnum
import xyz.drinkice.nahidalibrary.config.nshiro.getId
import xyz.drinkice.nahidalibrary.config.nshiro.getSecret
import xyz.drinkice.nahidalibrary.config.nshiro.getUsername
import xyz.drinkice.nahidalibrary.config.nshiro.token.PasswdToken
import xyz.drinkice.nahidalibrary.exception.UnauthorizedException
import xyz.drinkice.nahidalibrary.mapper.AccountMapper
import xyz.drinkice.nahidalibrary.model.AccountModel
import xyz.drinkice.nahidalibrary.service.AccountService
import xyz.drinkice.nahidalibrary.util.CommonUtils
import xyz.drinkice.nahidalibrary.util.JwtUtils
import xyz.drinkice.nahidalibrary.vo.LoginVo

@Service
class AccountServiceImpl : AccountService {
  
  private val logger = LoggerFactory.getLogger(AccountServiceImpl::class.java)
  
  @Autowired
  private lateinit var accountMapper: AccountMapper
  
  override fun login(username: String, password: String, isEmail: Boolean): LoginVo {
    val account = if (isEmail) {
      val wrapper = QueryWrapper<AccountModel>().eq(AccountModel::email.name, username)
      accountMapper.selectOne(wrapper)
        ?: throw UnauthorizedException(BizErrorTypeEnum.UNREGISTERED, "邮箱未绑定,或者账号未注册")
    } else {
      getOrCreate(username, password)
    }
    
    val subject = SecurityUtils.getSubject()
    try {
      subject.login(PasswdToken(account, password))
    } catch (e: AuthenticationException) {
      throw UnauthorizedException()
    }
    // perf: 异地登录
    return LoginVo(token = JwtUtils.sign(subject.getId(), subject.getUsername(), subject.getSecret()))
  }
  
  override fun getOrCreate(username: String, password: String): AccountModel {
    val wrapper = QueryWrapper<AccountModel>().eq(AccountModel::username.name, username)
    val account = accountMapper.selectOne(wrapper)
    if (account != null) {
      return account
    }
    // 没有账号则创建账号
    val md5Pwd = CommonUtils.md5(password)
    val newAccount = AccountModel(
      username = username,
      password = md5Pwd,
      secret = md5Pwd,
      nickname = username
    )
    accountMapper.insert(newAccount)
    return newAccount
  }
}