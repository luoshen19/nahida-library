package xyz.nahidalibrary.account.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.nahidalibrary.account.common.BizErrorResult
import xyz.nahidalibrary.account.common.BizErrorTypeEnum
import xyz.nahidalibrary.account.dto.LoginDto
import xyz.nahidalibrary.account.exception.VerificationException
import xyz.nahidalibrary.account.service.AccountService
import xyz.nahidalibrary.account.util.RegexMatchUtils
import xyz.nahidalibrary.account.vo.LoginVo


@RestController
class AuthController {
  
  private val logger = LoggerFactory.getLogger(AuthController::class.java)
  
  @Autowired
  private lateinit var accountService: AccountService
  
  /**
   * 登录 如果未注册则直接注册
   *
   * f3d693a10140c1647f371f930e3309e409d7658b
   */
  @PostMapping("/login")
  fun login(@RequestBody loginDto: LoginDto): ResponseEntity<LoginVo> {
    if (!RegexMatchUtils.isUsername(loginDto.username)) {
      throw VerificationException(message = "[username] 不符合规则")
    }
    if (!RegexMatchUtils.isPassword(loginDto.password)) {
      throw VerificationException(message = "[password] 不符合规则")
    }
    return ResponseEntity.ok(accountService.login(username = loginDto.username, password = loginDto.password))
  }
  
  @RequestMapping("/401")
  fun unauthorized(): ResponseEntity<BizErrorResult> =
    ResponseEntity(BizErrorResult(error = BizErrorTypeEnum.UNAUTHORIZED, message = "请登录"), HttpStatus.UNAUTHORIZED)
}