package cn.lijunkui.mybaties.annotation.mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.lijunkui.mybaties.annotation.ProductMapper;
import cn.lijunkui.mybaties.annotation.domain.Product;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTest {
	@Autowired
	private ProductMapper productMapper;
	
	@SuppressWarnings("deprecation")
	@Test
	public void findById() {
		Product product = productMapper.findById(1l);
		Assert.assertNotNull(product);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void findByCondition() {
		Product product = new Product();
		product.setProductName("苹");
		List<Product> findByCondition = productMapper.findByCondition(product);
		Assert.assertTrue(findByCondition.size()>0);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void insert() {
		Product product = new Product();
		product.setProductName("香蕉");
		product.setPrice(45d);
		product.setProductBrief("好吃的香蕉！");
		Long insert = productMapper.insert(product);
		Assert.assertTrue(insert > 0 );
	}
	@Test
	public void update() {
		Product product = new Product();
		product.setId(2l);
		product.setProductName("香蕉3");
		product.setPrice(45d);
		product.setProductBrief("好吃的香蕉！");
		productMapper.update(product);
	}
	
	@Test
	public void updateDynamic() {
		Product product = new Product();
		product.setId(2l);
		product.setProductName("香蕉4");
		productMapper.updateDynamic(product);
	}
	@Test
	@Transactional
	public void deleteById() {
		productMapper.deleteById(4l);
	}
}
