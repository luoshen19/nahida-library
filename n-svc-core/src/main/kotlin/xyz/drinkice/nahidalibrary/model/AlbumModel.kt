package xyz.drinkice.nahidalibrary.model

import com.baomidou.mybatisplus.annotation.TableName
import xyz.drinkice.nahidalibrary.anno.NoArg

@NoArg
@TableName("n_album")
data class AlbumModel(
  val name: String
) : BaseModel()
