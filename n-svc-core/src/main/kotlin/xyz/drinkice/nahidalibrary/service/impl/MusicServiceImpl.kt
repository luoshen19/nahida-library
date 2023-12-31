package xyz.drinkice.nahidalibrary.service.impl

import com.baomidou.mybatisplus.core.toolkit.Wrappers
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.drinkice.nahidalibrary.mapper.AlbumMapper
import xyz.drinkice.nahidalibrary.mapper.MusicMapper
import xyz.drinkice.nahidalibrary.service.MusicService
import xyz.drinkice.nahidalibrary.vo.AlbumItemVo
import xyz.drinkice.nahidalibrary.vo.ListAlbumVo
import xyz.drinkice.nahidalibrary.vo.ListMusicVo
import xyz.drinkice.nahidalibrary.vo.MusicItemVo

@Service
class MusicServiceImpl : MusicService {
  
  private val logger = LoggerFactory.getLogger(MusicServiceImpl::class.java)
  
  @Autowired
  private lateinit var musicMapper: MusicMapper
  
  @Autowired
  private lateinit var albumMapper: AlbumMapper
  
  override fun listAlbum() =
    ListAlbumVo(albumMapper.selectList(Wrappers.emptyWrapper())
      .map { AlbumItemVo(id = it.id!!, name = it.name) })
  
  override fun listMusic(albumId: Long) =
    ListMusicVo(musicMapper.listByAlbumId(albumId)
      .map { MusicItemVo(id = it.id!!, name = it.name, link = it.link) })
}