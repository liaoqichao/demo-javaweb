package hibernate.demo2.domain;

import java.util.List;

public class QueryResult<T> {

	private int tr;//totalRecord;�Ӽ�¼��
	private int firstResult;//��ǰҳ��һ����¼���±�
	private int maxResults;//ÿҳ���ļ�¼��
	private List<T> beanList;//��ҳ��ѯ�Ľ��
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	public int getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
	public int getTp(){//�����ҳ��
		return tr%maxResults==0?tr/maxResults:tr/maxResults+1;
	}
	@Override
	public String toString() {
		return "QueryResult [tr=" + tr + ", firstResult=" + firstResult + ", maxResults=" + maxResults + ", beanList="
				+ beanList + ", getTp()=" + getTp() + "]";
	}
	public QueryResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QueryResult(int tr, int firstResult, int maxResults, List<T> beanList) {
		super();
		this.tr = tr;
		this.firstResult = firstResult;
		this.maxResults = maxResults;
		this.beanList = beanList;
	}
	
	
	
}
