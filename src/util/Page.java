package util;

import java.util.List;
/**
 * @Author lilinying
 * @Date 2018/3/27 0027下午 3:06
 * @Version V1.0
 * Company 湖南智诚慧商信息技术有限公司
 *
 * 分页类
 */
public class Page<T> {

	// 实例化类的时候需要传递的
	private Integer curPage;// 当前页
	private Integer pageSize; // 每页显示条数
	private Integer totalRecords; // 总记录数

	// 计算获得的
	private Integer totalPages; // 总页数
	private Integer startIndex; // 查询起始值
	private Integer startPage; // 起始页码
	private Integer endPage; // 结束页码

	private List<T> pageData;// 页面数据
	
	private int statusName = 0;
	/**
	 * @return the statusName
	 */
	public int getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(int statusName) {
		this.statusName = statusName;
	}

	private String msgName = "";

	/**
	 * @return the msgName
	 */
	public String getMsgName() {
		return msgName;
	}

	/**
	 * @param msgName the msgName to set
	 */
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	public Page(Integer curPage, Integer pageSize, int totalRecords) {
		//如果当前页小于0页或者为空  默认显示一页
		if (curPage == null || curPage <= 0) {
			curPage = 1;
		}
		//如果页面数据条数为空或者<=0 默认显示两条
		if (pageSize == null || pageSize <= 0) {
			pageSize = 2;
		}
		
		
		this.pageSize = pageSize;
		this.totalRecords = totalRecords;
		//总页数
		this.totalPages = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
		//如果当前页大于总页数  显示当前页
		if (curPage > totalPages) {
			curPage = totalPages;
		}
		this.curPage = curPage;
		//起始值为（当前页-1）* 页面数据大小
		startIndex = (curPage - 1) * pageSize;
		if(startIndex<1){
			startIndex = 0;
		}
		// 如果总页数小于等于10时 显示所有页码
		if (totalPages <= 10) {
			startPage = 1;
			endPage = totalPages;
		} else {
			// 否则计算起始页码和结束页码
			if (this.curPage >= 7) {
				startPage = curPage - 5;
				endPage = curPage + 4;
			} else {
				startPage = 1;
				endPage = 10;
			}
			// 判断结束页码
			if (this.endPage > totalPages) {
				this.endPage = totalPages;
				this.startPage = this.endPage - 9;
			}
		}

	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public Integer getStartPage() {
		return startPage;
	}

	public Integer getEndPage() {
		return endPage;
	}

	@Override
	public String toString() {
		return "Page [curPage=" + curPage + ", pageSize=" + pageSize + ", totalRecords=" + totalRecords
				+ ", totalPages=" + totalPages + ", startIndex=" + startIndex + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", pageData=" + pageData + "]";
	}

}
