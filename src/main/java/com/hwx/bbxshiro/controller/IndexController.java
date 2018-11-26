package com.hwx.bbxshiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HuWanxin
 * @create 2018/11/20 14:49
 * @desc
 **/
@Controller
@RequestMapping(value = "/")
public class IndexController {


	@RequiresPermissions(value = {"add"})//判断权限标签，是否拥有user:add
	@RequestMapping(value = "/add")
	public String add() {
		return "user/add";
	}

	@RequiresPermissions(value = "update")
	@RequestMapping(value = "/update")
	public String update() {
		return "user/update";
	}

	@RequestMapping(value = "/toIndex")
	public String toIndex() {
		return "index";
	}

	@RequestMapping(value = "/noAuth")
	public String toNoAuth() {
		return "error/403";
	}


}
