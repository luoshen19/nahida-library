package xyz.drinkice.nahidalibrary.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.drinkice.nahidalibrary.service.MusicService

@RestController
@RequestMapping("/music")
class MusicController {
  
  @Autowired
  private lateinit var musicService: MusicService
  
  @GetMapping("/probe")
  fun probe(): ResponseEntity<Map<String, String>> {
    return ResponseEntity(mapOf("hello" to "world"), HttpStatus.OK)
  }
  
  @GetMapping("/listAlbum")
  fun listAlbum(): ResponseEntity<Map<String, String>> {
    val listAlbum = musicService.listAlbum()
    
    return ResponseEntity(mapOf("hello" to "world"), HttpStatus.OK)
  }
}