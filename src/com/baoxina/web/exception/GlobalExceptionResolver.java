package com.baoxina.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * 思路：
	系统遇到异常，在程序中手动抛出，dao抛给service、service给controller、controller抛给前端控制器，前端控制器调用全局异常处理器。
	全局异常处理器处理思路：
		解析出异常类型
		如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示
		如果该 异常类型不是系统 自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver{

	//handler就是处理器适配器要执行Handler对象（只有method）
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//解析出异常类型
		YiLiveException exception = null;
		if(ex instanceof YiLiveException){
			//如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示
			exception = (YiLiveException)ex;
		}else{
			//如果该 异常类型不是系统 自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）
			exception = new YiLiveException("未知错误");
		}
		//错误信息
		String exceptionMessage = exception.getMessage();
		
		ModelAndView modelAndView = new ModelAndView();
		//将错误信息传到页面
		modelAndView.addObject("exceptionMessage", exceptionMessage);
		//指向错误页面
		modelAndView.setViewName("error");
		return modelAndView;
	}
}