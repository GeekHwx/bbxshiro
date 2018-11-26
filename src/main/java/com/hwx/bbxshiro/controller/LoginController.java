package com.hwx.bbxshiro.controller;

import com.hwx.bbxshiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author HuWanxin
 * @create 2018/11/22 15:43
 * @desc
 **/
@Controller
public class LoginController {


	@RequestMapping("/toLogin")
	public String toLogin() {
		return "/login";
	}

	/**
	 * 用户登录
	 *
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public String loginUser(User user, boolean rememberMe, Model model) {
		//获取subject
		Subject subject = SecurityUtils.getSubject();
		//封装用户数据(用户名、密码、是否记住我[可去除])
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getUserPassword(),rememberMe);

		//执行登录判断
		try {
			subject.login(token);//执行UserRealm类中的认证方法

			return "redirect:/toIndex";
		} catch (UnknownAccountException e) {
			model.addAttribute("msg", "账号不存在，请重新输入！");
			return "login";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("msg", "密码错误，请重新输入！");
			return "login";
		}
	}

	/**
	 * 退出登录
	 *
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.removeAttribute("user");
		subject.logout();
		return "redirect:/toLogin";
	}


}
