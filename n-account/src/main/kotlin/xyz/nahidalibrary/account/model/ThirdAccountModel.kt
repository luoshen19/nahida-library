package xyz.nahidalibrary.account.model

import com.baomidou.mybatisplus.annotation.*

@TableName("n_third_account")
data class ThirdAccountModel(
  val accountId: String,
  val thirdId: String,
  val type: ThirdAccountType
): BaseModel()

enum class ThirdAccountType {
  GITHUB
}
