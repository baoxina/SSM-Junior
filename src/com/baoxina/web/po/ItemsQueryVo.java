package com.baoxina.web.po;

import java.util.List;

/**
 * 
 * <p>Description:商品包装对象 </p>
 */
public class ItemsQueryVo {
	
	//itemsList用于接收页面提交的批量数据
	private List<ItemsCustom> itemsList;

	//为了系统 可扩展性，对原始生成的po进行扩展
	private ItemsCustom itemsCustom;
	

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}

	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}

}
