package cn.lijunkui.controller;

import cn.lijunkui.domain.Hotel;
import cn.lijunkui.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * 旅馆 Controller
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     * 查询所有的旅馆信息
     * @return
     */
    @GetMapping("/hotel")
    public List<Hotel> findAll(){
        List<Hotel> hotelList = hotelService.findAll();
        return hotelList;
    }
}
