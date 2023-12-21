package xyz.nahidalibrary.account.controller.interceptor

import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class IpInterceptor : HandlerInterceptor {
  
  /**
   * IP等处理应该在网关解决
   */
  override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
    // 获取请求的 IP 地址
    val ipAddress = request.remoteAddr
//    println(ipAddress)
    return true
  }
}