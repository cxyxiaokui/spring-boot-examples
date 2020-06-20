package cn.lijunkui.dao;

import cn.lijunkui.config.TargetDataSource;
import cn.lijunkui.domain.Product;
import cn.lijunkui.enums.DataSourceKeyEnum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
@Mapper
@TargetDataSource(value = DataSourceKeyEnum.PRODUCT )
public interface ProductMapper {

    /**
     * 查询所有
     * @param
     * @return List<Hotel>
     */
    List<Product> selectList();
}
