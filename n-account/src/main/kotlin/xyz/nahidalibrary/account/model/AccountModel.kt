package xyz.nahidalibrary.account.model

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import xyz.nahidalibrary.account.anno.NoArg

@NoArg
@TableName("n_account")
data class AccountModel(
  val username: String,
  @TableField("'password'")
  val password: String?,
  /**用于JWT加密的密钥 与password相同，如果未设置则取随机值*/
  val secret: String,
  val nickname: String,
  val email: String? = null
): BaseModel()
