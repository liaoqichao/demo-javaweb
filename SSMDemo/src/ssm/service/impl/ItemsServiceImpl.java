package ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import ssm.exception.CustomException;
import ssm.mapper.ItemsMapper;
import ssm.mapper.ItemsMapperCustom;
import ssm.po.Items;
import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;
import ssm.service.ItemsService;

/**
 * ��Ʒ����ʵ����
 * @author Liaoqichao
 * @date 2016��4��18��
 */
public class ItemsServiceImpl implements ItemsService {

	/*
	 * ������applicationContext-dao.xml����ʹ���˰�ɨ�裬�Զ���Spring����
	 * ����Ҫд<bean id="itemsMapperCustom" class="">
	 */
	@Resource
	private ItemsMapperCustom itemsMapperCustom;
	@Resource
	private ItemsMapper itemsMapper;
	
	
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		// ͨ��ItemsMapperCustom��ѯ���ݿ�
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		
		Items items = itemsMapper.selectByPrimaryKey(id);
		/*
		 *  ����Ʒ��Ϣ����ҵ����
		 *  ���������Ʒ�����������ж���Ʒ�Ƿ���ڡ�
		 *  ��service�����ж��Ƿ���ڡ�ͨ��һ���µ�����������Ƿ���ڡ�������Է������
		 *  ����ItemsCustom
		 */
		// ...
		// ����һ����չ��
		
		// ������չ��
//		ItemsCustom itemsCustom = new ItemsCustom();
		
		/*
		 * ��Service���쳣
		 */
		if(items == null){
			throw new CustomException("��Ʒ��Ϣ�����ڣ�"); 
		}
		
		/*
		 * ��Controller���쳣��
		 * ���ֱ�Ӵ���ʵ������ôʵ����Ϊ�գ�����ʵ��������Ϊ�գ���Controller���쳣�ж�Ҫ��itemsCustomΪ���׳��쳣��
		 * ������Ĵ�����Զ��Ϊ�ա� 
		 */
		ItemsCustom itemsCustom = null;
		// ��items�����Կ�����itemsCustom
		if(items != null){
			itemsCustom = new ItemsCustom();
			BeanUtils.copyProperties(items, itemsCustom);
		}
		return itemsCustom;
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		// ���ҵ��У�飬ͨ����service�ӿ��жԹؼ���������У�顣
		// У��id�Ƿ�Ϊ�գ����Ϊ��(�׳��Զ����쳣)
		
		/*
		 *  ������Ʒ��Ϣ
		 *  ʹ��updateByPrimaryKeyWithBLOBs���Ը���id����items���е������ֶΣ��������ı����͡�
		 *  updateByPrimaryKeyWithBLOBsҪ�����bean�ı�����id
		 */
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

}
