package xyz.nahidalibrary.account.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import xyz.nahidalibrary.account.model.AccountModel

@Mapper
interface AccountMapper : BaseMapper<AccountModel> {
}