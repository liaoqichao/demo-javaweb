package ssm.po;

import java.util.List;

/**
 * ��Ʒ�İ�װ����
 * ��װ�˲�ѯ����
 * @author Liaoqichao
 * @date 2016��4��18��
 */
public class ItemsQueryVo {

	private Items items; // ��ѯ������������Ʒ��Ϣ
	
	private ItemsCustom itemsCustom; // ��Ʒ��Ϣ����չ����

	private List<ItemsCustom> itemsCustomList; // ������Ʒ��Ϣ
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
