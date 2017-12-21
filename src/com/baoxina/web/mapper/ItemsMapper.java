package com.baoxina.web.mapper;

import java.util.List;

import com.baoxina.web.po.Items;
import com.baoxina.web.po.ItemsCustom;
import com.baoxina.web.po.ItemsQueryVo;

public interface ItemsMapper {
	
	/**
	 * 根据商品名称  模糊查询  商品信息
	 * @param itemsQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	//根据id查询商品信息
	public Items findItemsById(int id) throws Exception;
	//更新商品信息
	public void updateItems(ItemsCustom items) throws Exception;

}
