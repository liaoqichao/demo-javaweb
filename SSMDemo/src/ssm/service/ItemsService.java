package ssm.service;

import java.util.List;

import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;

/**
 * ��Ʒ�����service�ӿ�
 * @author Liaoqichao
 * @date 2016��4��18��
 */
public interface ItemsService {

	/**
	 *  �õ���Ʒ��ѯ�б�
	 * @param itemsQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	/**
	 *  ����id��ѯ��Ʒ��Ϣ��Ϊʲô����������ItemsCustom������Items��
	 *  - ��Ϊ�����ݿ��ѯItems����ҵ�����ܻᴦ��������ݡ�������Ҫ��ItemsCustom����չItems�����ݣ�
	 *  	��ʾ��ҳ���ʱ���Items�����ݺ���չ���ݶ���ʾ��
	 * @param id
	 * @return ItemsCustom
	 * @throws Exception
	 */
	public ItemsCustom findItemsById(Integer id) throws Exception;
	
	/**
	 *  �޸���Ʒ��Ϣ������id�޸���Ʒ��Ϣ��ΪʲôҪ����id?��ItemsCustomҲ��Id����
	 *  - Э������Ϊ��������������Ա֪��id�Ǳ���ģ���ֹ����ItemsCustom��ʱ��Id�ǿյġ����԰�ItemsCustom��
	 *  	id��ȡ������
	 * @param id �޸���Ʒ��id
	 * @param itemsCustom �޸���Ʒ����Ϣ
	 * @throws Exception
	 */
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
}
