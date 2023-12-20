package xyz.nahidalibrary.account.config

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import org.apache.ibatis.reflection.MetaObject
import org.springframework.stereotype.Component
import xyz.nahidalibrary.account.model.BaseModel
import java.time.OffsetDateTime

/**
 * 数据库 插入或更新 自动填充字段
 */
@Component
class BaseModelHandler : MetaObjectHandler {
  /**
   * 插入填充
   * - createAt: now
   * - deleted: false
   */
  override fun insertFill(metaObject: MetaObject) {
    // 起始版本 3.3.3(推荐)
    this.strictInsertFill(metaObject, BaseModel::createAt.name, { OffsetDateTime.now() }, OffsetDateTime::class.java)
    this.strictInsertFill(metaObject, BaseModel::deleted.name, { false }, Boolean::class.java)
  }
  
  /**
   * 更新填充
   * - updateAt: now
   */
  override fun updateFill(metaObject: MetaObject) {
    // 起始版本 3.3.3(推荐)
    this.strictUpdateFill(metaObject, BaseModel::updateAt.name, { OffsetDateTime.now() }, OffsetDateTime::class.java)
  }
}