package com.hwx.bbxshiro.config;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HuWanxin
 * @create 2018/11/23 14:15
 * @desc
 **/
@ControllerAdvice
public class exceptionController {

	/**
	 * 处理访问方法时权限不足问题
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = UnauthorizedException.class)
	public String defaultErrorHandler(HttpServletRequest req, Exception e) {
		return "error/403";
	}


}
