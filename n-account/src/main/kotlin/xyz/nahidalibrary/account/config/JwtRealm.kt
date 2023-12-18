package xyz.nahidalibrary.account.config

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import xyz.nahidalibrary.account.common.BizErrorTypeEnum
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
    val username = JwtUtils.getUsername(token)
      ?: throw BizException(BizErrorTypeEnum.UNAUTHORIZED, "认证失败")
    val wrapper = QueryWrapper<AccountModel>().eq(AccountModel::username.name, username)
    val account = accountMapper.selectOne(wrapper)
      ?: throw BizException(BizErrorTypeEnum.UNAUTHORIZED, "认证失败")
    if (!JwtUtils.verify(token = token, username = username, secret = account.secret)) {
      throw BizException(BizErrorTypeEnum.UNAUTHORIZED, "认证失败")
    }
    return SimpleAuthenticationInfo(token, token, "jwtRealm")
  }
  
  /**
   * 鉴权
   */
  override fun doGetAuthorizationInfo(principal: PrincipalCollection?): AuthorizationInfo? {
    return null
  }
}