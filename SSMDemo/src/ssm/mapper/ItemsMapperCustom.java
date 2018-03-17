package ssm.mapper;

import java.util.List;

import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;

public interface ItemsMapperCustom {
    
	/**
	 * 查询商品列表
	 * @param itemsQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
}