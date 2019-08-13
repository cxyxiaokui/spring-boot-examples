package cn.lijunkui.thymeleaf.officialexamples.product.model;

public class Comment {
	 
    private Integer id = null;//评论id
    private String text = null;//评论内容
    
    
    public Comment() {
        super();
    }
 
    public Comment(final Integer id, final String text) {
        this.id = id;
        this.text = text;
    }
 
    public Integer getId() {
        return this.id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }
 
 
    public String getText() {
        return this.text;
    }
    public void setText(final String text) {
        this.text = text;
    }
}
