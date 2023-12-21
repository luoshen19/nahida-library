package xyz.nahidalibrary.account.config.nshiro

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator
import org.apache.shiro.mgt.DefaultSubjectDAO
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import xyz.nahidalibrary.account.config.nshiro.realm.JwtRealm
import xyz.nahidalibrary.account.config.nshiro.realm.PasswdRealm
import javax.servlet.Filter


@Configuration
open class ShiroConfig {
  
  @Bean("shiroSecurityManager")
  open fun shiroSecurityManager(
    passwdRealm: PasswdRealm,
    jwtRealm: JwtRealm
  ): DefaultWebSecurityManager =
    DefaultWebSecurityManager().apply {
      this.setRealms(listOf(passwdRealm, jwtRealm))
      closeSession(this)
    }
  
  @Bean("shiroFilterFactoryBean")
  open fun shiroFilterFactoryBean(manager: DefaultWebSecurityManager): ShiroFilterFactoryBean =
    ShiroFilterFactoryBean().apply {
      val jwtFilterName = "jwt"
      filters = mutableMapOf<String, Filter>(
        jwtFilterName to JwtFilter()
      )
      securityManager = manager
      unauthorizedUrl = "/401"
      /**
       * 自定义url规则
       * @see <a href="http://shiro.apache.org/web.html#urls-">自定义url规则</a>
       */
      filterChainDefinitionMap = mutableMapOf(
        "/test/jwt" to jwtFilterName,
        "/401" to "anon"
      )
    }
  
  /**
   * 关闭shiro自带的session，详情见文档
   * @see <a href="http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29">关闭shiro自带的session</a>
   */
  private fun closeSession(manager: DefaultWebSecurityManager) {
    val subjectDAO = DefaultSubjectDAO().apply {
      sessionStorageEvaluator = DefaultSessionStorageEvaluator().apply { isSessionStorageEnabled = false }
    }
    manager.setSubjectDAO(subjectDAO)
  }
}