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
 * 商品管理实现类
 * @author Liaoqichao
 * @date 2016年4月18日
 */
public class ItemsServiceImpl implements ItemsService {

	/*
	 * 在整合applicationContext-dao.xml里面使用了包扫描，自动让Spring管理，
	 * 不需要写<bean id="itemsMapperCustom" class="">
	 */
	@Resource
	private ItemsMapperCustom itemsMapperCustom;
	@Resource
	private ItemsMapper itemsMapper;
	
	
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		// 通过ItemsMapperCustom查询数据库
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		
		Items items = itemsMapper.selectByPrimaryKey(id);
		/*
		 *  对商品信息进行业务处理。
		 *  例如根据商品的生产日期判断商品是否过期。
		 *  在service处理，判断是否过期。通过一个新的属性来标记是否过期。这个属性放在哪里？
		 *  放在ItemsCustom
		 */
		// ...
		// 返回一个扩展类
		
		// 定义扩展类
//		ItemsCustom itemsCustom = new ItemsCustom();
		
		/*
		 * 在Service抛异常
		 */
		if(items == null){
			throw new CustomException("商品信息不存在！"); 
		}
		
		/*
		 * 在Controller抛异常：
		 * 如果直接创建实例，那么实例不为空，但是实例的内容为空，到Controller的异常判断要求itemsCustom为空抛出异常。
		 * 用上面的代码永远不为空。 
		 */
		ItemsCustom itemsCustom = null;
		// 将items的属性拷贝到itemsCustom
		if(items != null){
			itemsCustom = new ItemsCustom();
			BeanUtils.copyProperties(items, itemsCustom);
		}
		return itemsCustom;
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		// 添加业务校验，通常在service接口中对关键参数进行校验。
		// 校验id是否为空，如果为空(抛出自定义异常)
		
		/*
		 *  更新商品信息
		 *  使用updateByPrimaryKeyWithBLOBs可以根据id更新items表中的所有字段，包括大文本类型。
		 *  updateByPrimaryKeyWithBLOBs要求传入的bean的必须有id
		 */
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

}
