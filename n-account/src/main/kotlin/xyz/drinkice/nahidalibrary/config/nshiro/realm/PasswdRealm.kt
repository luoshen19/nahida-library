package xyz.drinkice.nahidalibrary.config.nshiro.realm

import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.credential.CredentialsMatcher
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import xyz.drinkice.nahidalibrary.config.nshiro.CurrentAccount
import xyz.drinkice.nahidalibrary.config.nshiro.PRINCIPAL_KEY
import xyz.drinkice.nahidalibrary.config.nshiro.token.PasswdToken
import xyz.drinkice.nahidalibrary.util.CommonUtils

/**
 * Passwd 即 Password
 * 专门用于login接口做 账号或邮箱密码 登录
 */
@Configuration
open class PasswdRealm : AuthorizingRealm() {
  
  private val logger = LoggerFactory.getLogger(PasswdRealm::class.java)
  
  override fun supports(token: AuthenticationToken): Boolean {
    return token is PasswdToken
  }
  
  /**
   * 认证
   */
  override fun doGetAuthenticationInfo(authToken: AuthenticationToken): AuthenticationInfo? {
    val token = authToken as PasswdToken
    val currentAccount = CurrentAccount(token.principal.id!!, token.principal.username, token.principal.secret)
    return SimpleAuthenticationInfo(currentAccount, token.principal.password, PRINCIPAL_KEY)
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
    val tokenCredentials = token.credentials as String
    val accountCredentials = info.credentials as String
    return accountCredentials == CommonUtils.md5(tokenCredentials)
  }
}