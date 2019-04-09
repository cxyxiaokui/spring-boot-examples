package cn.lijunkui.thymeleaf.officialexamples.product;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import cn.lijunkui.thymeleaf.officialexamples.product.model.Product;
import cn.lijunkui.thymeleaf.officialexamples.product.model.User;
import cn.lijunkui.thymeleaf.officialexamples.product.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private LocaleResolver localeResolver;
	private  ProductService productService = new ProductService();
	  
	@RequestMapping("/")
	public String useT(Model model,HttpServletRequest request) {
		//获取所有的商品
		List<Product> allProducts = productService.findAll(); 
		model.addAttribute("prods", allProducts);
		//获取当前日期
		model.addAttribute("today", Calendar.getInstance());
		//设置访问用户信息到session
		request.getSession(true).setAttribute("user", new User("桌前", "明月", "CHINA", null));
		
		return "productList";
	}
}
