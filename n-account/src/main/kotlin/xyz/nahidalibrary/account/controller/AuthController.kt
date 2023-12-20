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
import xyz.nahidalibrary.account.dto.LoginByCodeDto
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
   * 账号-密码 登录，有以下几种情况
   * - 账号未注册 则注册
   * - 账号使用的是邮箱 未注册 则直接提示未注册
   * 邮箱是账号注册之后绑定的
   * f3d693a10140c1647f371f930e3309e409d7658b
   */
  @PostMapping("/login")
  fun login(@RequestBody loginDto: LoginDto): ResponseEntity<LoginVo> {
    /*
      各种网站的密码规则都不一样，有时候为了满足某个网站的规则，我把常用的密码格式都改了，但是下次登录就容易密码错误。
      然后再去找回密码，发现只是少一个特殊字符的限制，如果提示不符合密码规则，也许体验会好很多。
      再者这个应用不需要怎么保护。。。
     */
    if (!RegexMatchUtils.isPassword(loginDto.password)) {
      throw VerificationException(message = "[password] 不符合规则")
    }
    // 账号
    if (!RegexMatchUtils.isUsername(loginDto.password)) {
      val loginVo = accountService.login(username = loginDto.username, password = loginDto.password)
      return ResponseEntity.ok(loginVo)
    }
    // 邮箱
    if (RegexMatchUtils.isEmail(loginDto.username)) {
      val loginVo = accountService.login(username = loginDto.username, password = loginDto.password, isEmail = true)
      return ResponseEntity.ok(loginVo)
    }
    
    throw VerificationException(message = "[username | email] 不符合规则")
  }
  
  /**
   * 邮箱-验证码 登录
   */
  @PostMapping("/login.code")
  fun loginByCode(@RequestBody loginDto: LoginByCodeDto): ResponseEntity<LoginVo> {
    TODO()
  }
  
  @RequestMapping("/401")
  fun unauthorized(): ResponseEntity<BizErrorResult> =
    ResponseEntity(BizErrorResult(error = BizErrorTypeEnum.UNAUTHORIZED, message = "请登录"), HttpStatus.UNAUTHORIZED)
}