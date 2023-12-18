package xyz.nahidalibrary.account.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.apache.shiro.crypto.hash.Md5Hash
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.nahidalibrary.account.mapper.AccountMapper
import xyz.nahidalibrary.account.model.AccountModel
import xyz.nahidalibrary.account.service.AccountService

@Service
class AccountServiceImpl : AccountService {
  
  @Autowired
  private lateinit var accountMapper: AccountMapper
  
  override fun getOrCreate(username: String, password: String): AccountModel {
    val wrapper = QueryWrapper<AccountModel>().eq(AccountModel::username.name, username)
      .or().eq(AccountModel::email.name, username)
    val account = accountMapper.selectOne(wrapper)
    if (account != null) {
      return account
    }
    // 没有账号则创建账号
    val md5Pwd = Md5Hash(password).toHex()
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