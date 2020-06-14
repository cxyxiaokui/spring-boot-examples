package cn.lijunkui.domain;
/**
 * 商品Model类
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
public class Product {

    private Long id;
    /**商品名称*/
    private String productName;
    /**商品价格*/
    private Double price;
    /**商品简介*/
    private String productBrief;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductBrief() {
        return productBrief;
    }

    public void setProductBrief(String productBrief) {
        this.productBrief = productBrief;
    }
}
