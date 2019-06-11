package cn.lijunkui.mybaties.xml.mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.lijunkui.mybaties.xml.domain.Hotel;
import cn.lijunkui.mybaties.xml.page.Page;
import cn.lijunkui.mybaties.xml.param.HotalParam;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HotelMapperTest {
	@Autowired
	private HotelMapper hotelMapper;
	
	@SuppressWarnings("deprecation")
	@Test
	public void selectByCityId() {
		Hotel result = hotelMapper.selectByCityId(1l);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void insert(){
		Hotel hotel = new Hotel();
		hotel.setName("如家");
		hotel.setCity("河北");
		hotel.setAddress("朝阳区富明路112号");
		hotelMapper.insert(hotel);
	}
	@Test
	public void selectHotelList(){
		List<Hotel> selectHotelList = hotelMapper.selectHotelList(new Hotel());
		System.out.println("------------------------------------");
	}
	@Test
	public void selectHotelListByPage(){
		HotalParam hotalParam = new HotalParam();
		List<Hotel> hotelList = hotelMapper.selectHotelListByPage(hotalParam);
		List<Hotel> allHotel = hotelMapper.selectHotelList(new Hotel());
		Page<Hotel> page = new Page();
		page.setItems(hotelList);
		page.setPageSize(hotalParam.getPageSize());
		page.setCurrentPage(hotalParam.getCurrentPage());
		page.setTotalCount(allHotel.size());
		System.out.println("------------------------------------");
		HotalParam hotalParam2 = new HotalParam();
		hotalParam2.setCurrentPage(2);
		List<Hotel> hotelList2 = hotelMapper.selectHotelListByPage(hotalParam2);
		System.out.println("------------------------------------");
	}
	@Test
	public void deleteById(){
		hotelMapper.deleteById(1L);
	}
	@Test
	public void deleteByIds(){
		Long [] ids = {2L,3L};
		hotelMapper.deleteByIds(ids);
	}
	@Test
	public void update(){
		Hotel hotel = new Hotel();
		hotel.setId(4L);
		hotel.setCity("天津");
		hotelMapper.update(hotel);
	}
}
