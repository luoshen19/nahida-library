package xyz.drinkice.nahidalibrary.service.impl

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import xyz.drinkice.nahidalibrary.service.MusicService

@SpringBootTest
class MusicServiceImplTest {
  
  @Autowired
  private lateinit var musicService: MusicService
  
  @Test
  fun listAlbum() {
    musicService.listAlbum().albumList.forEach { println(it) }
  }
  
  @Test
  fun listMusic() {
    musicService.listMusic(2).musicList.forEach { println(it) }
  }
}