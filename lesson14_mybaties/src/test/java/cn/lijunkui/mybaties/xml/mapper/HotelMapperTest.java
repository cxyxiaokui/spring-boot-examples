package cn.lijunkui.mybaties.xml.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.lijunkui.mybaties.xml.domain.Hotel;

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
}
