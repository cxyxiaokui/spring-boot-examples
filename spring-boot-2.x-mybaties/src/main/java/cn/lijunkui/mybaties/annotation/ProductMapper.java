package cn.lijunkui.mybaties.annotation;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.lijunkui.mybaties.annotation.domain.Product;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
@Mapper
public interface ProductMapper {
	@Results(id="product" ,value= { 
			@Result(property = "id", column = "id", id = true),
			@Result(property = "productName", column = "product_Name"),
			@Result(property = "price", column = "price"),
			@Result(property = "productBrief", column = "product_Brief")
	})
	@Select("select * from product where id = #{id}")
	public Product findById(@Param("id") Long id);
	
	/**
	 * 条件查询
	 * @param product
	 * @return
	 */
	
	@SelectProvider(type = ProductProvider.class, method = "findByCondition")
	public List<Product> findByCondition(Product product);
	/**
	 * 添加商品
	 * @param product
	 * @return Long 表示影响的行数
	 */
	@Insert("insert into product (product_Name, price,product_Brief) values(#{productName}, #{price}, #{productBrief})")
	@Options(useGeneratedKeys=true,keyProperty="id")
	@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = long.class)
	public Long insert(Product product);
	
	/**
	 * 修改商品
	 * @param product
	 */
	@Update("update product set product_Name=#{productName} , price= #{price} , product_Brief = #{productBrief} where  id=#{id}")
	public void update(Product product);
	/**
	 * 动态修改商品
	 * @param product
	 */
	@UpdateProvider(type = ProductProvider.class, method = "updateDynamic")
	public void updateDynamic(Product product);
	/**
	 * 删除商品
	 * @param id
	 */
	 @Delete("delete from product where id=#{id}")
	 public void deleteById(long id);
}
