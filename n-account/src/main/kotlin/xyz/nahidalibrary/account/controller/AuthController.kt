package xyz.nahidalibrary.account.controller

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.nahidalibrary.account.common.BizErrorResult
import xyz.nahidalibrary.account.common.BizErrorTypeEnum
import xyz.nahidalibrary.account.dto.LoginDto
import xyz.nahidalibrary.account.exception.UnauthorizedException


@RestController
class AuthController {
  
  /**
   * 登录 如果未注册则直接注册
   * f3d693a10140c1647f371f930e3309e409d7658b
   */
  @PostMapping("/login")
  fun login(@RequestBody loginDto: LoginDto): String {
//    if (!Regex("^[a-zA-Z][a-zA-Z0-9_]{6,11}\$").matches(loginDto.username)) {
//      throw VerificationException(message = "[username] 不符合规则")
//    }
//    if (!Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+\$).{6,10}\$").matches(loginDto.password)) {
//      throw VerificationException(message = "[password] 不符合规则")
//    }
    
    val subject = SecurityUtils.getSubject()
    val token = UsernamePasswordToken(loginDto.username, loginDto.password)
    
    try {
      subject.login(token)
    } catch (e: AuthenticationException) {
      throw UnauthorizedException()
    }
//    JwtUtils.sign(subject.principals, subject.)
    
    return "ok " + subject.principals
  }
  
  @RequestMapping("/401")
  fun unauthorized(): ResponseEntity<BizErrorResult> =
    ResponseEntity(BizErrorResult(error = BizErrorTypeEnum.UNAUTHORIZED, message = "请登录"), HttpStatus.UNAUTHORIZED)
}