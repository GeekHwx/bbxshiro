package com.hwx.bbxshiro.service;


import com.hwx.bbxshiro.entity.User;

/**
 * @author HuWanxin
 * @create 2018/11/20 16:28
 * @desc
 **/
public interface UserService {

	User getUserByName(String name);

	User findById(Long id);


}
