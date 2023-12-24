package xyz.drinkice.nahidalibrary.vo

data class ListMusicVo(
  val musicList: List<MusicItemVo>
)

data class MusicItemVo(
  val id: Long,
  val name: String
)
