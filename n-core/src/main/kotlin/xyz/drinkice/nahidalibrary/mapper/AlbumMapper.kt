package xyz.drinkice.nahidalibrary.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import xyz.drinkice.nahidalibrary.model.AlbumModel

@Mapper
interface AlbumMapper : BaseMapper<AlbumModel> {
}