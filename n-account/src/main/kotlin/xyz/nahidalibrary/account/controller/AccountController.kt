package xyz.nahidalibrary.account.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountController {
  
  @GetMapping("/probe")
  fun probe(): String {
    println("hello world")
    return "ok"
  }
}