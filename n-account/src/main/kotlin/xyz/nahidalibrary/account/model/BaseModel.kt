package xyz.nahidalibrary.account.model

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import java.time.OffsetDateTime

abstract class BaseModel {
  @TableId(type = IdType.AUTO)
  val id: Long? = null
  @TableField(fill = FieldFill.INSERT)
  val createAt: OffsetDateTime? = null
  @TableField(fill = FieldFill.UPDATE)
  val updateAt: OffsetDateTime? = null
  @TableField(value = "is_deleted", fill = FieldFill.INSERT)
  val deleted: Boolean = false
}