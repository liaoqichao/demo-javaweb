package ssm.po;

import java.util.List;

/**
 * 商品的包装对象
 * 包装了查询条件
 * @author Liaoqichao
 * @date 2016年4月18日
 */
public class ItemsQueryVo {

	private Items items; // 查询条件可能是商品信息
	
	private ItemsCustom itemsCustom; // 商品信息的扩展属性

	private List<ItemsCustom> itemsCustomList; // 批量商品信息
	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}

	public List<ItemsCustom> getItemsCustomList() {
		return itemsCustomList;
	}

	public void setItemsCustomList(List<ItemsCustom> itemsCustomList) {
		this.itemsCustomList = itemsCustomList;
	}
	
}
