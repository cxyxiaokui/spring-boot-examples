package cn.lijunkui.controller;

import cn.lijunkui.domain.Product;
import cn.lijunkui.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 查询所有的商品信息
	 * @return
	 */
	@GetMapping("/product")
	public List<Product> findAll() {
        List<Product> productList = productService.findAll();
        return productList;
	}
}
