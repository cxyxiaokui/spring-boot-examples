package cn.lijunkui.mybaties.annotation;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import cn.lijunkui.mybaties.annotation.domain.Product;

public class ProductProvider {
	   public String updateDynamic(Product product) {  
		   	return new SQL() {{
       	   UPDATE("product");
       	   if (!StringUtils.isEmpty(product.getProductName())) {
       		   SET("product_Name = #{productName}");
       	   }
       	   if (product.getPrice()!=null) {
       		   SET("price = #{price}");
       	   }
       	   if(!StringUtils.isEmpty(product.getProductBrief()))
       		   SET("product_Brief = #{productBrief}");
       	   }}.toString();
      }
	   
	   public String findByCondition(Product product) {
		   return new SQL() {{
			   SELECT("id,product_Name as productName ,price,product_Brief as productBrief");
			   FROM("product");
			   if (!StringUtils.isEmpty(product.getProductName())) {
				   WHERE("product_Name like CONCAT('%',#{productName},'%')");
       	   }
			   if (product.getPrice()!=null) {
				   WHERE("price = #{price} ");
       	   }
       	  
		   }}.toString();
	   }
}
