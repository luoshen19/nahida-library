package xyz.nahidalibrary.account.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import xyz.nahidalibrary.account.controller.interceptor.IpInterceptor


@Configuration
open class WebConfig : WebMvcConfigurer {
  
  override fun addInterceptors(registry: InterceptorRegistry) {
    // 注册 IPInterceptor 拦截器，并指定拦截的路径
    registry.addInterceptor(IpInterceptor()).addPathPatterns("/**")
  }
}