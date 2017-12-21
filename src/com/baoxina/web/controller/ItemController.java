package com.baoxina.web.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baoxina.web.po.ItemsCustom;
import com.baoxina.web.po.ItemsQueryVo;
import com.baoxina.web.service.ItemsService;
import com.baoxina.web.validation.ValidateGroup1;

/**
 * 
 * RequestMapping注解的作用： 1.url映射：定义controller方法对应的url，进行处理器映射使用
 * 2.窄化请求映射：定义controller对应的url根路径，进行处理器映射使用
 * 3.限制http请求方法：出于安全性考虑，对http的链接进行方法限制。如果限制请求为post方法，进行get请求，报错。
 * 
 * controller方法的返回值 >>返回ModelAndView: 需要方法结束时，定义ModelAndView，将model和view分别进行设置。
 * >>返回string: 1.表示返回逻辑视图名。真正视图(jsp路径)=前缀+逻辑视图名+后缀
 * 2.redirect重定向。修改提交的request数据无法传到重定向的地址
 * 3.forward页面转发。通过forward进行页面转发，浏览器地址栏url不变，request可以共享。 >>返回void:
 * 在controller方法形参上可以定义request和response，使用request或response指定响应结果：
 * 1、使用request转向页面，如下： request.getRequestDispatcher("页面路径").forward(request,
 * response); 2、也可以通过response页面重定向： response.sendRedirect("url")
 * 3、也可以通过response指定响应结果，例如响应json数据如下： response.setCharacterEncoding("utf-8");
 * response.setContentType("application/json;charset=utf-8");
 * response.getWriter().write("json串");
 * 
 */
@Controller
// 为了对url进行分类管理，可以再这里定义[根路径]，最终访问url是 [根路径] + [子路径]
// 比如：商品列表：/items/queryItems.action
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemsService itemsService;

	/**
	 * [返回值]为[ModelAndView]
	 */
	@RequestMapping(value = "/findItemsList", method = RequestMethod.GET)
	public ModelAndView findItemsList() throws Exception {
		ItemsQueryVo itemsQueryVo = new ItemsQueryVo();

		ItemsCustom itemsCustom = new ItemsCustom();
		itemsCustom.setName("台式机");
		itemsQueryVo.setItemsCustom(itemsCustom);

		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}

	/**
	 * [返回值]为[string]
	 */
	@RequestMapping(value = "/editItems", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String editItems(Model model,
			@RequestParam(value = "id", required = true) Integer items_id)
			throws Exception {
		ItemsCustom items = itemsService.findItemsById(items_id);
		// 通过形参中的model将model数据传到页面
		// 相当于modelAndView.addObject方法
		model.addAttribute("items", items);
		return "items/editItems";
	}

	/**
	 * [返回值]为[void]
	 */
	/*
	 * @RequestMapping(value="/editItemsSubmit",method={RequestMethod.POST,
	 * RequestMethod.GET}) public void editItemsSubmit(HttpServletRequest
	 * request,HttpServletResponse response) throws Exception{
	 * response.sendRedirect("/WEB-INF/jsp/success.jsp"); }
	 */
	// 在需要校验的pojo前边添加@Validated，在需要校验的pojo后边添加BindingResult
	// bindingResult接收校验出错信息
	// 注意：@Validated和BindingResult bindingResult是配对出现，并且形参顺序是固定的（一前一后）。
	// value={ValidateGroup1.class}指定使用ValidateGroup1分组的校验
	// @ModelAttribute可以指定pojo回显到页面在request中的key
	@RequestMapping(value = "/editItemsSubmit", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String editItemsSubmit(
			Model model,
			@ModelAttribute("items") @Validated(value = { ValidateGroup1.class }) ItemsCustom itemsCustom,
			BindingResult bindingResult, MultipartFile items_pic/*接收商品图片的变量*/)
			throws Exception {
		if (bindingResult != null && bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			return "items/editItems";
		}
		if(items_pic != null){
			//文件的原始名称
			String originalFilename = items_pic.getOriginalFilename();
			if(originalFilename != null && originalFilename.length() > 0){
				//存储的文件的新名称
				String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				//图片的存储路径
				String fileBasePath = "D:\\develop\\upload\\temp\\";
				File newFile = new File(fileBasePath + newFileName);
				//原始文件的数据写入新文件
				items_pic.transferTo(newFile);
				//将新图片的名称设置到pojo中进行持久化，并回显
				itemsCustom.setPic(newFileName);
			}
		}
		itemsService.updateItems(itemsCustom.getId(), itemsCustom);
		return "success";
	}

	@RequestMapping(value = "/queryItems", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView queryItems(@ModelAttribute("itemsQueryVo") ItemsQueryVo itemsQueryVo) throws Exception {
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}

	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] items_ids) throws Exception {
		// 此处调用service的批量删除方法
		return "success";
	}

	// 跳转批量修改商品页面，先将商品信息查询出来，在页面中可以编辑商品信息
	@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(ItemsQueryVo itemsQueryVo)
			throws Exception {
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/editItemsQuery");
		return modelAndView;
	}

	// 批量修改的商品信息的提交方法
	// 通过ItemsQueryVo接收批量提交的商品信息，将页面提交的多个商品信息存储到itemsQueryVo中的itemsList属性中
	@RequestMapping("/batchEditItemsSubmit")
	public String batchEditItemsSubmit(ItemsQueryVo itemsQueryVo)
			throws Exception {
		List<ItemsCustom> itemsList = itemsQueryVo.getItemsList();
		System.out.println(itemsList);
		return "success";
	}

	// 商品分类
	// @ModelAttribute表示最终将方法返回值放在request域中，key为指定的itemtypes
	@ModelAttribute("itemtypes")
	public Map<String, String> getItemTypes() {
		Map<String, String> itemTypes = new HashMap<String, String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");
		itemTypes.put("103", "影音");
		itemTypes.put("104", "服装");
		return itemTypes;
	}
	
	//查询商品信息，输出json
	//   /itemsView/{id}中的{id}表示占位符，通过@PathVariable获取占位符中的参数
	//如果占位符中的名称和形参名一致，@PathVariable可以不指定名称
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer items_id) throws Exception{
		//调用service查询商品信息
		return itemsService.findItemsById(items_id);
	}
}