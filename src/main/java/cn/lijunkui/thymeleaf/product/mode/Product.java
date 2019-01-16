package cn.lijunkui.thymeleaf.product.mode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {

	 
    private Integer id = null;//商品id
    private String name = null;//商品名称
    private BigDecimal price = null;//商品价格
    private boolean inStock = false;//是否有货
    private List<Comment> comments = new ArrayList<Comment>();//评论
    
    
    public Product() {
        super();
    }
 
 
    public Product(final Integer id, final String name, final boolean inStock, final BigDecimal price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }
 
 
    public Integer getId() {
        return this.id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }
 
 
    public String getName() {
        return this.name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    
 
    public BigDecimal getPrice() {
        return this.price;
    }
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
 
 
    public boolean isInStock() {
        return this.inStock;
    }
    public void setInStock(final boolean inStock) {
        this.inStock = inStock;
    }
    
    
    public List<Comment> getComments() {
        return this.comments;
    }
}
