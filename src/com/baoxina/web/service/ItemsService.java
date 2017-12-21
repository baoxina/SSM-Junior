package com.baoxina.web.service;

import java.util.List;

import com.baoxina.web.po.ItemsCustom;
import com.baoxina.web.po.ItemsQueryVo;

public interface ItemsService {

	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	
	public ItemsCustom findItemsById(Integer id) throws Exception;
	
	
	public void updateItems(Integer id,ItemsCustom items) throws Exception;
	
}
