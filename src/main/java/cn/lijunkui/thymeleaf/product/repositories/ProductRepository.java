package cn.lijunkui.thymeleaf.product.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.lijunkui.thymeleaf.product.mode.Comment;
import cn.lijunkui.thymeleaf.product.mode.Product;

public class ProductRepository {

	 
    private static final ProductRepository INSTANCE = new ProductRepository();
    private final Map<Integer,Product> productsById;
    
    
    
    public static ProductRepository getInstance() {
        return INSTANCE;
    }
    
    
    private ProductRepository() {
        
        super();
        
        this.productsById = new LinkedHashMap<Integer, Product>();
 
 
        final Product prod1 = new Product(1, "鲜甜罗勒", true, new BigDecimal("4.99"));
        final Product prod2 = new Product(2, "意大利番茄", false, new BigDecimal("1.25"));
        final Product prod3 = new Product(3, "黄柿子椒", true, new BigDecimal("2.50"));
        final Product prod4 = new Product(4, "车达奶酪", true, new BigDecimal("18.75"));
        final Product prod5 = new Product(5, "超纯椰子油", true, new BigDecimal("6.34"));
        final Product prod6 = new Product(6, "有机番茄酱", true, new BigDecimal("1.99"));
        final Product prod7 = new Product(7, "全麦片麦片粥", true, new BigDecimal("3.07"));
        final Product prod8 = new Product(8, "传统西红柿罗勒酱", true, new BigDecimal("2.58"));
        final Product prod9 = new Product(9, "藜麦粉", true, new BigDecimal("3.02"));
        final Product prod10 = new Product(10, "蜜柚汁", true, new BigDecimal("2.58"));
        final Product prod11 = new Product(11, "100%纯枫糖浆", true, new BigDecimal("5.98"));
        final Product prod12 = new Product(12, "意大利面酱", false, new BigDecimal("2.08"));
        final Product prod13 = new Product(13, "香草酥麦片", false, new BigDecimal("1.75"));
        final Product prod14 = new Product(14, "初榨橄榄油", false, new BigDecimal("5.01"));
        final Product prod15 = new Product(15, "烤蒜意大利面酱", true, new BigDecimal("2.40"));
        final Product prod16 = new Product(16, "蔬菜浓汤罐头", true, new BigDecimal("2.19"));
        final Product prod17 = new Product(17, "杏仁乳1L", true, new BigDecimal("3.24"));
        final Product prod18 = new Product(18, "有机鸡肉野米汤", true, new BigDecimal("3.17"));
        final Product prod19 = new Product(19, "紫胡萝卜、黑莓、藜麦和希腊酸奶", true, new BigDecimal("8.88"));
        final Product prod20 = new Product(20, "南瓜胡萝卜胡萝卜汁", false, new BigDecimal("3.90"));
        final Product prod21 = new Product(21, "有机菜籽油", true, new BigDecimal("10.13"));
        final Product prod22 = new Product(22, "马铃薯玉米卷饼", true, new BigDecimal("2.44"));
        final Product prod23 = new Product(23, "玉米杂烩汤罐头", true, new BigDecimal("2.30"));
        final Product prod24 = new Product(24, "有机柠檬汁", true, new BigDecimal("2.48"));
        final Product prod25 = new Product(25, "辣罗勒酱", true, new BigDecimal("4.72"));
        final Product prod26 = new Product(26, "甜龙舌兰蜜", true, new BigDecimal("6.46"));
        final Product prod27 = new Product(27, "黑烤花生酱", false, new BigDecimal("3.48"));
        final Product prod28 = new Product(28, "无糖柠檬绿茶", true, new BigDecimal("18.34"));
        final Product prod29 = new Product(29, "全谷物薄片谷物", true, new BigDecimal("3.52"));
        final Product prod30 = new Product(30, "燕麦卷", true, new BigDecimal("4.00"));
 
 
        this.productsById.put(prod1.getId(), prod1);
        this.productsById.put(prod2.getId(), prod2);
        this.productsById.put(prod3.getId(), prod3);
        this.productsById.put(prod4.getId(), prod4);
        this.productsById.put(prod5.getId(), prod5);
        this.productsById.put(prod6.getId(), prod6);
        this.productsById.put(prod7.getId(), prod7);
        this.productsById.put(prod8.getId(), prod8);
        this.productsById.put(prod9.getId(), prod9);
        this.productsById.put(prod10.getId(), prod10);
        this.productsById.put(prod11.getId(), prod11);
        this.productsById.put(prod12.getId(), prod12);
        this.productsById.put(prod13.getId(), prod13);
        this.productsById.put(prod14.getId(), prod14);
        this.productsById.put(prod15.getId(), prod15);
        this.productsById.put(prod16.getId(), prod16);
        this.productsById.put(prod17.getId(), prod17);
        this.productsById.put(prod18.getId(), prod18);
        this.productsById.put(prod19.getId(), prod19);
        this.productsById.put(prod20.getId(), prod20);
        this.productsById.put(prod21.getId(), prod21);
        this.productsById.put(prod22.getId(), prod22);
        this.productsById.put(prod23.getId(), prod23);
        this.productsById.put(prod24.getId(), prod24);
        this.productsById.put(prod25.getId(), prod25);
        this.productsById.put(prod26.getId(), prod26);
        this.productsById.put(prod27.getId(), prod27);
        this.productsById.put(prod28.getId(), prod28);
        this.productsById.put(prod29.getId(), prod29);
        this.productsById.put(prod30.getId(), prod30);
        
        
        prod2.getComments().add(new Comment(1, "I'm so sad this product is no longer available!"));
        prod2.getComments().add(new Comment(2, "When do you expect to have it back?"));
 
        prod13.getComments().add(new Comment(3, "Very tasty! I'd definitely buy it again!"));
        prod13.getComments().add(new Comment(4, "My kids love it!"));
        prod13.getComments().add(new Comment(5, "Good, my basic breakfast cereal. Though maybe a bit in the sweet side..."));
        prod13.getComments().add(new Comment(6, "Not that I find it bad, but I think the vanilla flavouring is too intrusive"));
        prod13.getComments().add(new Comment(7, "I agree with the excessive flavouring, but still one of my favourites!"));
        prod13.getComments().add(new Comment(8, "Cheaper than at the local store!"));
        prod13.getComments().add(new Comment(9, "I'm sorry to disagree, but IMO these are far too sweet"));
        prod13.getComments().add(new Comment(10, "Good. Pricey though."));
 
 
        prod9.getComments().add(new Comment(11, "Made bread with this and it was great!"));
        prod9.getComments().add(new Comment(12, "Note: this comes actually mixed with wheat flour"));
 
        prod14.getComments().add(new Comment(13, "Awesome Spanish oil. Buy it now."));
        prod14.getComments().add(new Comment(14, "Would definitely buy it again. Best one I've tasted"));
        prod14.getComments().add(new Comment(15, "A bit acid for my taste, but still a very nice one."));
        prod14.getComments().add(new Comment(16, "Definitely not the average olive oil. Really good."));
 
        prod16.getComments().add(new Comment(17, "Great value!"));
 
        prod24.getComments().add(new Comment(18, "My favourite :)"));
 
        prod30.getComments().add(new Comment(19, "Too hard! I would not buy again"));
        prod30.getComments().add(new Comment(20, "Taste is OK, but I agree with previous comment that bars are too hard to eat"));
        prod30.getComments().add(new Comment(21, "Would definitely NOT buy again. Simply unedible!"));
 
 
    }
    
    
    
    public List<Product> findAll() {
        return new ArrayList<Product>(this.productsById.values());
    }
    
    public Product findById(final Integer id) {
        return this.productsById.get(id);
    }
}
