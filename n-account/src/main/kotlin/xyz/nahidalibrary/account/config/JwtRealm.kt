package xyz.nahidalibrary.account.config

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import xyz.nahidalibrary.account.mapper.AccountMapper
import xyz.nahidalibrary.account.model.AccountModel

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
//    val username = JwtUtils.getUsername(authToken.credentials as String)
//      ?: throw BizException()
    
    
    val usernamePasswordToken = authToken as UsernamePasswordToken
    val wrapper = QueryWrapper<AccountModel>()
    wrapper.eq(AccountModel::username.name, usernamePasswordToken.username)
    return accountMapper.selectOne(wrapper)?.let { SimpleAuthenticationInfo(it, it.password, "jwtRealm") }
  }
  
  /**
   * 鉴权
   */
  override fun doGetAuthorizationInfo(principal: PrincipalCollection?): AuthorizationInfo? {
    return null
  }
}