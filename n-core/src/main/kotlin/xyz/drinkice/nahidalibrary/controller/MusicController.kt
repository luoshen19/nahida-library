package xyz.drinkice.nahidalibrary.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.drinkice.nahidalibrary.service.MusicService
import xyz.drinkice.nahidalibrary.vo.ListAlbumVo
import xyz.drinkice.nahidalibrary.vo.ListMusicVo

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
  fun listAlbum(): ResponseEntity<ListAlbumVo> =
    ResponseEntity.ok(musicService.listAlbum())
  
  @GetMapping("/listMusic")
  fun listMusic(albumId: Long): ResponseEntity<ListMusicVo> =
    ResponseEntity.ok(musicService.listMusic(albumId))
}