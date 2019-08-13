package cn.lijunkui.mybaties.xml.page;

public class PageInfo {
	private Integer pageSize = 10;
    private Integer currentPage= 1;
    private Integer startIndex;
    
	public Integer getStartIndex() {
		this.startIndex = (currentPage-1) * pageSize;
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
}
