package xyz.nahidalibrary.account.config

import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.crypto.hash.Md5Hash
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import xyz.nahidalibrary.account.exception.UnauthorizedException
import xyz.nahidalibrary.account.service.AccountService

/**
 * Passwd 即 Password
 * 专门用于login接口做 账号或邮箱密码 登录
 */
@Configuration
open class PasswdRealm : AuthorizingRealm() {
  
  private val logger = LoggerFactory.getLogger(PasswdRealm::class.java)
  
  @Autowired
  private lateinit var accountService: AccountService
  
  override fun supports(token: AuthenticationToken): Boolean {
    return token is UsernamePasswordToken
  }
  
  /**
   * 认证
   */
  override fun doGetAuthenticationInfo(authToken: AuthenticationToken): AuthenticationInfo? {
    val token = authToken as UsernamePasswordToken
    val account = accountService.getOrCreate(token.username, token.password.toString())
    // 密码校验
    if (account.password != Md5Hash(token.password).toHex()) {
      logger.info("UnauthorizedException: 账号/邮箱 或 密码错误")
      throw UnauthorizedException()
    }
    return SimpleAuthenticationInfo(account.username, account.secret, PasswdRealm::class.java.simpleName)
  }
  
  /**
   * 鉴权
   */
  override fun doGetAuthorizationInfo(principal: PrincipalCollection?): AuthorizationInfo? {
    return null
  }
}