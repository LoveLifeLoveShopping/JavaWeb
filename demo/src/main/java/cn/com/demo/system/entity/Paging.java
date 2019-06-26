package cn.com.demo.system.entity;

/**
 * 分页
 * 
 * @author min
 *
 */
public class Paging {

	// 当前页
	private int currentPage;
	// 每页行数
	private int linage;
	// 总页数
	private int totalPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLinage() {
		return linage;
	}

	public void setLinage(int linage) {
		this.linage = linage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "当前页是：" + this.currentPage + "总页数是：" + this.totalPage + "每页行数是:" + this.linage;
	}

}
