package xyz.nahidalibrary.account.config.nshiro.realm

import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.authc.credential.CredentialsMatcher
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import xyz.nahidalibrary.account.config.nshiro.CurrentAccount
import xyz.nahidalibrary.account.config.nshiro.PRINCIPAL_KEY
import xyz.nahidalibrary.account.service.AccountService
import xyz.nahidalibrary.account.util.CommonUtils

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
    val account = try {
      accountService.getOrCreate(token.username, String(token.password))
    } catch (e: Exception) {
      logger.error("getOrCreate", e)
      throw e
    }
    val currentAccount = CurrentAccount(account.id!!, account.username, account.secret)
    return SimpleAuthenticationInfo(currentAccount, account.password, PRINCIPAL_KEY)
  }
  
  /**
   * 鉴权
   */
  override fun doGetAuthorizationInfo(principal: PrincipalCollection?): AuthorizationInfo? {
    return null
  }
  
  override fun setCredentialsMatcher(credentialsMatcher: CredentialsMatcher) {
    super.setCredentialsMatcher(PasswdCredentialsMatcher())
  }
}

class PasswdCredentialsMatcher : CredentialsMatcher {
  override fun doCredentialsMatch(token: AuthenticationToken, info: AuthenticationInfo): Boolean {
    val tokenCredentials = token as UsernamePasswordToken
    val accountCredentials = info.credentials as String
    return accountCredentials == CommonUtils.md5(tokenCredentials.password)
  }
}