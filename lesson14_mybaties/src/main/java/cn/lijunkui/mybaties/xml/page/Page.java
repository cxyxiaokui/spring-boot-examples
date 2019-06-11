package cn.lijunkui.mybaties.xml.page;

import java.util.List;

public class Page<T> extends PageInfo {

    private Integer totalCount;
    private List<T> items;
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	
}
