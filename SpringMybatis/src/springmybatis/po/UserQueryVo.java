package springmybatis.po;

import java.util.ArrayList;
import java.util.List;

/**
 * demo5 = �������Ϊ��װ���͵�pojo
 * demo9 = ���idList��������id��ʹ��foreach��ǩ����
 * �ŵ㣺���Դ���ͼ�㴫��dao�㶼ֻ���������
 * @author Liaoqichao
 * @date 2016��3��13��
 */
public class UserQueryVo {

	// ��װ����Ҫ�Ĳ�ѯ����
	private UserCustom userCustom;
	// ��װ��������Ʒ�ȵ�...

	// ������id
	private List<Integer> idList = new ArrayList<Integer>();
	
	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
	
	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
}
