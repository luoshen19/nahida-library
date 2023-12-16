package xyz.nahidalibrary.account.config

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator
import org.apache.shiro.mgt.DefaultSubjectDAO
import org.apache.shiro.realm.Realm
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
open class ShiroConfig {
  
  @Bean("jwtSecurityManager")
  open fun jwtSecurityManager(realm: Realm): DefaultWebSecurityManager {
    val manager = DefaultWebSecurityManager()
    manager.setRealm(realm)
    /**
     * 关闭shiro自带的session，详情见文档
     * @see <a href="http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29">关闭shiro自带的session</a>
     */
    val subjectDAO = DefaultSubjectDAO().apply {
      sessionStorageEvaluator = DefaultSessionStorageEvaluator().apply { isSessionStorageEnabled = false }
    }
    manager.setSubjectDAO(subjectDAO);
    return manager
  }
  
  @Bean("jwtShiroFilterFactoryBean")
  open fun shiroFilterFactoryBean(manager: DefaultWebSecurityManager): ShiroFilterFactoryBean =
    ShiroFilterFactoryBean().apply {
      filters = mapOf(JwtFilter::class.java.name to JwtFilter())
      securityManager = manager
      unauthorizedUrl = "/401"
      /**
       * 自定义url规则
       * @see <a href="http://shiro.apache.org/web.html#urls-">自定义url规则</a>
       */
      filterChainDefinitionMap = mapOf(
//        "/test/jwt" to JwtFilter::class.java.name,
        "/401" to "anon"
      )
    }
}