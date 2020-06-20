package cn.lijunkui.dao;

import java.util.List;

import cn.lijunkui.config.TargetDataSource;
import cn.lijunkui.enums.DataSourceKeyEnum;
import org.apache.ibatis.annotations.Mapper;
import cn.lijunkui.domain.Hotel;
/**
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
@Mapper
@TargetDataSource(value = DataSourceKeyEnum.HOTEL )
public interface HotelMapper {


	/**
	 * 查询所有
	 * @return List<Hotel>
	 */
	List<Hotel> selectList();
}
