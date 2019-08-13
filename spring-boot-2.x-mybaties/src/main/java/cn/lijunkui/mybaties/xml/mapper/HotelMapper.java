package cn.lijunkui.mybaties.xml.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.lijunkui.mybaties.xml.domain.Hotel;
import cn.lijunkui.mybaties.xml.param.HotalParam;

@Mapper
public interface HotelMapper {
	Hotel selectByCityId(long id);
	
	List<Hotel> selectHotelList(Hotel hotel);
	
	List<Hotel> selectHotelListByPage(HotalParam hotalParam);
	
	void deleteById(long id);
	
	void deleteByIds(Long[] ids);
	
	void update(Hotel hotel);
	
	void insert(Hotel hotel);
}
