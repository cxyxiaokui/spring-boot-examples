package cn.lijunkui.service;

import cn.lijunkui.dao.HotelMapper;
import cn.lijunkui.domain.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
@Service
public class HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    public List<Hotel> findAll(){
        List<Hotel> hotels = hotelMapper.selectList();
        return hotels;
    }
}
