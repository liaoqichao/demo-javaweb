package company.crm.domain;

import java.util.List;

public class PageBean<T> {

	private int pc; //��ǰҳ��pageCode��serlvet�����ȡ
//	private int tp;	//��ҳ��totalPage	 pageBean�����Լ������
	private int tr; //�ܼ�¼��totalRecord dao����
	private int ps; //ÿҳ��¼��pageSize servlet�����Լ���
	private List<T> beanList;//��ǰҳ�ļ�¼ dao����
	private String url; //url������� serlvet���𡣽�ȡurl�ʺ�֮��Ĳ���
	
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
		int tp = tr/ps;	//ͨ���ܼ�¼����ÿҳ��¼������¼��ҳ��
		return tr%ps==0?tp:tp+1;//ÿҳ10����¼��100����¼����10ҳ��101����¼����10+1ҳ
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
