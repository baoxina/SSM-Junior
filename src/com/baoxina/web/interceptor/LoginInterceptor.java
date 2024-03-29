package com.baoxina.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//获取请求的url
		String requestURI = request.getRequestURI();
		if(requestURI != null && requestURI.length() > 0){
			//判断url是否是公开 地址（实际使用时将公开 地址配置配置文件中）
			//这里公开地址是登陆提交的地址
			if(requestURI.indexOf("login.action") != -1){
				//如果进行登陆提交，放行
				return true;
			}
			//判断session
			HttpSession session = request.getSession();
			if(session != null){
				//从session中取出用户身份信息
				String username = (String) session.getAttribute("username");
				if(username != null){
					//身份存在，放行
					return true;
				}
			}
		}
		//执行这里表示用户身份需要认证，跳转登陆页面
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
