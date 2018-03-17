package ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 实现登录拦截
 * @author Liaoqichao
 * @date 2016年5月13日
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	// 登录
	@RequestMapping("/login")
	public String login(Model model, HttpSession session,String username, String password) throws Exception{
		
		// 调用service进行身份登录验证
		// ...
		
		session.setAttribute("session_username", username);
		
		// 重定向到商品列表页面
		return "redirect:/items/queryItems.action";
	}
	
	// 退出
	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session) throws Exception{
		
		// 让session过期
		session.invalidate();
		
		// 重定向到商品列表页面
		return "redirect:/items/queryItems.action";
	}
}
