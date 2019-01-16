package cn.lijunkui.thymeleaf.product.service;

import java.util.List;

import cn.lijunkui.thymeleaf.product.mode.Product;
import cn.lijunkui.thymeleaf.product.repositories.ProductRepository;

public class ProductService {
	public ProductService() {
        super();
    }
    
    public List<Product> findAll() {
        return ProductRepository.getInstance().findAll();
    }
 
    public Product findById(final Integer id) {
        return ProductRepository.getInstance().findById(id);
    }
}
