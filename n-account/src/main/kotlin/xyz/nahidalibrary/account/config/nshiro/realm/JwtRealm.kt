package xyz.nahidalibrary.account.config.nshiro.realm

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.credential.CredentialsMatcher
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import xyz.nahidalibrary.account.common.BizErrorTypeEnum
import xyz.nahidalibrary.account.config.nshiro.CurrentAccount
import xyz.nahidalibrary.account.config.nshiro.PRINCIPAL_KEY
import xyz.nahidalibrary.account.config.nshiro.token.JwtToken
import xyz.nahidalibrary.account.exception.BizException
import xyz.nahidalibrary.account.mapper.AccountMapper
import xyz.nahidalibrary.account.model.AccountModel
import xyz.nahidalibrary.account.util.JwtUtils

/**
 * JWT 接口校验
 */
@Configuration
open class JwtRealm : AuthorizingRealm() {
  
  private val logger = LoggerFactory.getLogger(JwtRealm::class.java)
  
  @Autowired
  private lateinit var accountMapper: AccountMapper
  
  override fun supports(token: AuthenticationToken?): Boolean {
    return token is JwtToken
  }
  
  /**
   * 认证
   */
  override fun doGetAuthenticationInfo(authToken: AuthenticationToken): AuthenticationInfo? {
    val token = authToken.credentials as String
    val wrapper = QueryWrapper<AccountModel>().eq(AccountModel::id.name, JwtUtils.getId(token))
    val account = accountMapper.selectOne(wrapper)
      ?: throw BizException(BizErrorTypeEnum.UNAUTHORIZED, "认证失败")
    val currentAccount = CurrentAccount(account.id!!, account.username, account.secret)
    return SimpleAuthenticationInfo(currentAccount, account.secret, PRINCIPAL_KEY)
  }
  
  /**
   * 鉴权
   */
  override fun doGetAuthorizationInfo(principal: PrincipalCollection?): AuthorizationInfo? {
    return null
  }
  
  override fun setCredentialsMatcher(credentialsMatcher: CredentialsMatcher) {
    super.setCredentialsMatcher(JwtCredentialsMatcher())
  }
}

class JwtCredentialsMatcher : CredentialsMatcher {
  override fun doCredentialsMatch(token: AuthenticationToken, info: AuthenticationInfo): Boolean {
    val tokenCredentials = token.credentials as String
    val accountCredentials = info.credentials as String
    return JwtUtils.verify(token = tokenCredentials, secret = accountCredentials)
  }
}