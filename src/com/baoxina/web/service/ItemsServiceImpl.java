package com.baoxina.web.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.baoxina.web.exception.YiLiveException;
import com.baoxina.web.mapper.ItemsMapper;
import com.baoxina.web.po.Items;
import com.baoxina.web.po.ItemsCustom;
import com.baoxina.web.po.ItemsQueryVo;

public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsMapper itemsMapper;
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception {
		List<ItemsCustom> itemsList = itemsMapper.findItemsList(itemsQueryVo);
		return itemsList;
	}
	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		Items items = itemsMapper.findItemsById(id);
		if(items == null){
			throw new YiLiveException("修改的用户不存在！");
		}
		//中间对商品信息进行业务处理。。。
		//最终返回扩展的pojo
		ItemsCustom itemsCustom = new ItemsCustom();
		BeanUtils.copyProperties(items, itemsCustom);
		return itemsCustom;
	}
	@Override
	public void updateItems(Integer id, ItemsCustom items) throws Exception {
		//添加业务校验，通常在service接口对关键参数进行校验
		//校验id是否为空，如果为空抛出异常
		items.setId(id);
		itemsMapper.updateItems(items);
	}
}
