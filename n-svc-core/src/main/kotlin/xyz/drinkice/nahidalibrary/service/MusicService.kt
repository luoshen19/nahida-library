package xyz.drinkice.nahidalibrary.service

import xyz.drinkice.nahidalibrary.vo.ListAlbumVo
import xyz.drinkice.nahidalibrary.vo.ListMusicVo

interface MusicService {
  
  fun listAlbum(): ListAlbumVo
  
  fun listMusic(albumId: Long): ListMusicVo
}