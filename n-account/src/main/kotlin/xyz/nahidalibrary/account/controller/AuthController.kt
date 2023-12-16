package xyz.nahidalibrary.account.controller

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.UsernamePasswordToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.nahidalibrary.account.common.BizErrorEnum
import xyz.nahidalibrary.account.common.BizErrorResult
import xyz.nahidalibrary.account.dto.LoginDto


@RestController
class AuthController {
  
  /**
   * 登录 如果未注册则直接注册
   */
  @PostMapping("/login")
  fun login(@RequestBody loginDto: LoginDto): String {
    val currentUser = SecurityUtils.getSubject()
    val token = UsernamePasswordToken(loginDto.username, loginDto.password)
    
    currentUser.login(token)
    return "ok"
  }
  
  @RequestMapping("/401")
  fun unauthorized(): ResponseEntity<BizErrorResult> =
    ResponseEntity(BizErrorResult(error = BizErrorEnum.UNAUTHORIZED, message = "请登录"), HttpStatus.UNAUTHORIZED)
}