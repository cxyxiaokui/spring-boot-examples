package cn.lijunkui.mail;

public class InlineResource {
	private String cid;
	private String path;
	
	public InlineResource(String cid,String path) {
		this.cid = cid;
		this.path = path;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
