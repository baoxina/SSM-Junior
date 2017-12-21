package com.baoxina.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baoxina.web.po.ItemsCustom;

@RequestMapping("/testJson")
@Controller
public class TestJsonController {

	//请求K-V串，返回json串
	@RequestMapping(value="/responseJson.action",method={RequestMethod.POST})
	public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom){
		//@ResponseBody将itemsCustom转成json输出
		return itemsCustom;
	}
	//请求json串(商品信息),输出json(商品信息)
	//@RequestBody将请求的商品信息的json转换成itemsCustom对象
	//@ResponseBody将itemsCustom对象转换成json串输出
	@RequestMapping(value="/requestJson.action",method={RequestMethod.POST})
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom){
		return itemsCustom;
	}
}
