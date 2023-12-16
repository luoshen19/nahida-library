package xyz.nahidalibrary.account.model

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import java.time.OffsetDateTime

abstract class BaseModel {
  @TableId
  val id: Long? = null
  val createAt: OffsetDateTime? = null
  val updateAt: OffsetDateTime? = null
  @TableField("'is_deleted'")
  val deleted: Boolean = false
}