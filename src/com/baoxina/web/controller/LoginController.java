package com.baoxina.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String login(HttpSession session,@RequestParam(value="username",required=true) String username,@RequestParam(value="password",required=true) String password) throws Exception{
		
		//调用service查询数据库，进行用户验证
		//......
		
		//在session中保存用户身份信息
		session.setAttribute("username", username);
		
		//重定向到商品列表页面
		return "redirect:/items/queryItems.action";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception{
		// 清除session
		session.invalidate();
		//重定向到商品列表页面
		return "redirect:/items/queryItems.action";
	}
}
