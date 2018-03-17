package ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ʵ�ֵ�¼����
 * @author Liaoqichao
 * @date 2016��5��13��
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	// ��¼
	@RequestMapping("/login")
	public String login(Model model, HttpSession session,String username, String password) throws Exception{
		
		// ����service������ݵ�¼��֤
		// ...
		
		session.setAttribute("session_username", username);
		
		// �ض�����Ʒ�б�ҳ��
		return "redirect:/items/queryItems.action";
	}
	
	// �˳�
	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session) throws Exception{
		
		// ��session����
		session.invalidate();
		
		// �ض�����Ʒ�б�ҳ��
		return "redirect:/items/queryItems.action";
	}
}
