package company.crm.domain;

import java.util.List;

public class PageBean<T> {

	private int pc; //当前页码pageCode，serlvet负责获取
//	private int tp;	//总页数totalPage	 pageBean可以自己算出来
	private int tr; //总记录数totalRecord dao负责
	private int ps; //每页记录数pageSize servlet负责自己定
	private List<T> beanList;//当前页的记录 dao负责
	private String url; //url后的条件 serlvet负责。截取url问号之后的参数
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTp() {
		int tp = tr/ps;	//通过总记录数和每页记录数来记录总页数
		return tr%ps==0?tp:tp+1;//每页10条记录，100条记录返回10页，101条记录返回10+1页
	}
//	public void setTp(int tp) {
//		this.tp = tp;
//	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
	@Override
	public String toString() {
		return "PageBean [pc=" + pc + ", tr=" + tr + ", ps=" + ps + ", beanList=" + beanList + ", getTp()=" + getTp()
				+ "]";
	}
	
	
}
