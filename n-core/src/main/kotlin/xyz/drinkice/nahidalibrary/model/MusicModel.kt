package xyz.drinkice.nahidalibrary.model

import com.baomidou.mybatisplus.annotation.TableName
import xyz.drinkice.nahidalibrary.common.anno.NoArg

@NoArg
@TableName("n_music")
data class MusicModel(
  val name: String,
  val trainer: String,
  val trainerLink: String
) : BaseModel()