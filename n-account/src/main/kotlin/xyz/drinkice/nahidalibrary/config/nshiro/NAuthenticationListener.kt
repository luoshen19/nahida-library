package xyz.drinkice.nahidalibrary.config.nshiro

import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationListener
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.subject.PrincipalCollection
import org.slf4j.LoggerFactory

class NAuthenticationListener : AuthenticationListener {
  
  private val logger = LoggerFactory.getLogger(NAuthenticationListener::class.java)
  
  /**
   * 认证成功时通知
   */
  override fun onSuccess(token: AuthenticationToken?, info: AuthenticationInfo?) {
    logger.info("success")
  }
  
  override fun onFailure(token: AuthenticationToken?, ae: AuthenticationException?) {
  
  }
  
  override fun onLogout(principals: PrincipalCollection?) {

  }
}