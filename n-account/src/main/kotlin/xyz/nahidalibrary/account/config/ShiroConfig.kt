package xyz.nahidalibrary.account.config

import org.apache.shiro.realm.Realm
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
open class ShiroConfig {
  
  @Bean
  open fun mySecurityManager(realm: Realm): DefaultWebSecurityManager {
    val manager = DefaultWebSecurityManager()
    manager.setRealm(realm)
    return manager
  }
  
  @Bean
  open fun shiroFilterFactoryBean(manager: DefaultWebSecurityManager): ShiroFilterFactoryBean {
    val shiroFilterFactoryBean = ShiroFilterFactoryBean()
    shiroFilterFactoryBean.securityManager = manager
    
    val filterMap = HashMap<String, String>()
    filterMap["/mobile/**"] = "authc"
    filterMap["/salary/**"] = "authc"
    shiroFilterFactoryBean.filterChainDefinitionMap = filterMap
    
    return shiroFilterFactoryBean
  }
}