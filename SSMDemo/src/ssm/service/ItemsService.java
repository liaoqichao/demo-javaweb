package ssm.service;

import java.util.List;

import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;

/**
 * 商品管理的service接口
 * @author Liaoqichao
 * @date 2016年4月18日
 */
public interface ItemsService {

	/**
	 *  得到商品查询列表
	 * @param itemsQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	/**
	 *  根据id查询商品信息。为什么返回类型是ItemsCustom而不是Items？
	 *  - 因为从数据库查询Items，在业务层可能会处理过的数据。所以需要用ItemsCustom，扩展Items的内容，
	 *  	显示到页面的时候把Items的内容和扩展内容都显示。
	 * @param id
	 * @return ItemsCustom
	 * @throws Exception
	 */
	public ItemsCustom findItemsById(Integer id) throws Exception;
	
	/**
	 *  修改商品信息。根据id修改商品信息。为什么要传入id?，ItemsCustom也有Id啊？
	 *  - 协作开发为了让其他开发人员知道id是必须的，防止传入ItemsCustom的时候Id是空的。所以把ItemsCustom的
	 *  	id提取出来。
	 * @param id 修改商品的id
	 * @param itemsCustom 修改商品的信息
	 * @throws Exception
	 */
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
}
