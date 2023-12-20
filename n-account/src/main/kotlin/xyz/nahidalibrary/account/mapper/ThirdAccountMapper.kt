package xyz.nahidalibrary.account.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import xyz.nahidalibrary.account.model.AccountModel
import xyz.nahidalibrary.account.model.ThirdAccountModel

@Mapper
interface ThirdAccountMapper : BaseMapper<ThirdAccountModel> {
}