package xyz.nahidalibrary.account.config

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import xyz.nahidalibrary.account.mapper.AccountMapper
import xyz.nahidalibrary.account.model.AccountModel

@Configuration
open class MyRealm : AuthorizingRealm() {
  
  @Autowired
  private lateinit var accountMapper: AccountMapper
  
  override fun doGetAuthenticationInfo(token: AuthenticationToken?): AuthenticationInfo? {
    val usernamePasswordToken = token as UsernamePasswordToken
    return LambdaQueryWrapper<AccountModel>()
      .eq(AccountModel::username, usernamePasswordToken.username)
      .or()
      .eq(AccountModel::email, usernamePasswordToken.username)
      .let { accountMapper.selectOne(it) }
      ?.let { SimpleAuthenticationInfo(it, it.password, "myRealm") }
  }
  
  override fun doGetAuthorizationInfo(principalCollection: PrincipalCollection?): AuthorizationInfo? {
    println("hello")
    return null
  }
}