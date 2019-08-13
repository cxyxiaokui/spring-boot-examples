package cn.lijunkui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.lijunkui.mybaties.annotation.ProductMapper;
import cn.lijunkui.mybaties.annotation.domain.Product;

@RestController
public class ProductController {
	@Autowired
	private ProductMapper productMapper;
	@GetMapping("/productList/{id}")
	public Product findById(@PathVariable(name="id") Long id) {
		Product findById = productMapper.findById(id);
		return findById;
	}
}
