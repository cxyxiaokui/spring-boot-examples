package cn.lijunkui.mybaties.xml.mapper;
import org.apache.ibatis.annotations.Mapper;

import cn.lijunkui.mybaties.xml.domain.Hotel;

@Mapper
public interface HotelMapper {
	Hotel selectByCityId(long id);
}
