package xyz.drinkice.nahidalibrary.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {
  
  @GetMapping("/probe")
  fun probe(): ResponseEntity<Map<String, String>> {
    return ResponseEntity(mapOf("hello" to "world"), HttpStatus.OK)
  }

}