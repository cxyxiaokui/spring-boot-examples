package cn.lijunkui.thymeleaf.officialexamples.product.service;

import java.util.List;

import cn.lijunkui.thymeleaf.officialexamples.product.model.Product;
import cn.lijunkui.thymeleaf.officialexamples.product.repositories.ProductRepository;

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
