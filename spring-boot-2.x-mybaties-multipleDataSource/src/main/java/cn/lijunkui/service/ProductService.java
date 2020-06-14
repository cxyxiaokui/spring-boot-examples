package cn.lijunkui.service;

import cn.lijunkui.dao.ProductMapper;
import cn.lijunkui.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> findAll(){
        return productMapper.selectList();
    }
}
