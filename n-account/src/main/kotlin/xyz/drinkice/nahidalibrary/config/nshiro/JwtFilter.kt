package xyz.drinkice.nahidalibrary.config.nshiro

import org.apache.shiro.web.filter.authc.BearerHttpAuthenticationFilter
import org.slf4j.LoggerFactory
import xyz.drinkice.nahidalibrary.config.nshiro.token.JwtToken
import java.io.IOException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter : BearerHttpAuthenticationFilter() {
  
  private val logger = LoggerFactory.getLogger(JwtFilter::class.java)
  
  override fun executeLogin(request: ServletRequest, response: ServletResponse): Boolean {
    val httpServletRequest = request as HttpServletRequest
    val token = JwtToken(httpServletRequest.getHeader("Authorization").replace("Bearer ", ""))
    // 提交给realm进行登入，如果错误他会抛出异常并被捕获
    getSubject(request, response).login(token)
    // 如果没有抛出异常则代表登入成功，返回true
    return true;
  }
  
  override fun isAccessAllowed(request: ServletRequest, response: ServletResponse, mappedValue: Any?): Boolean {
    if (isLoginAttempt(request, response)) {
      try {
        executeLogin(request, response)
      } catch (e: Exception) {
        response401(response)
      }
    }
    return true;
  }
  
  /**
   * 对跨域提供支持
   */
//  override fun preHandle(request: ServletRequest, response: ServletResponse): Boolean {
//    val httpServletRequest = request as HttpServletRequest
//    val httpServletResponse = response as HttpServletResponse
//    httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"))
//    httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE")
//    httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"))
//    // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
//    if (httpServletRequest.method.equals(RequestMethod.OPTIONS.name)) {
//      httpServletResponse.status = HttpStatus.OK.value()
//      return false
//    }
//    return super.preHandle(request, response);
//  }
  
  /**
   * 将非法请求跳转到 /401
   */
  private fun response401(response: ServletResponse) {
    try {
      val httpServletResponse = response as HttpServletResponse
      httpServletResponse.sendRedirect("/401")
    } catch (e: IOException) {
      logger.error("JwtFilter", e)
    }
  }
}