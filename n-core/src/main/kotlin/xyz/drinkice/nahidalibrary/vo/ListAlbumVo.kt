package xyz.drinkice.nahidalibrary.vo

data class ListAlbumVo(
  val albumList: List<AlbumItemVo>
)

data class AlbumItemVo(
  val id: Long,
  val name: String
)
