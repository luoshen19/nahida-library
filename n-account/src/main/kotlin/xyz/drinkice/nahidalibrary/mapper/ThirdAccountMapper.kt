package xyz.drinkice.nahidalibrary.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import xyz.drinkice.nahidalibrary.model.ThirdAccountModel

@Mapper
interface ThirdAccountMapper : BaseMapper<ThirdAccountModel> {
}