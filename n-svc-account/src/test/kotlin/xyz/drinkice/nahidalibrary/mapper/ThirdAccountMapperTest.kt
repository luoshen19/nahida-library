package xyz.drinkice.nahidalibrary.mapper

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import xyz.drinkice.nahidalibrary.model.ThirdAccountModel


@SpringBootTest
class ThirdAccountMapperTest {
  
  @Autowired
  private lateinit var thirdAccountMapper: ThirdAccountMapper
  
  @Test
  fun testSelect() {
    val list: List<ThirdAccountModel> = thirdAccountMapper.selectList(null)
    list.forEach(System.out::println)
  }
}