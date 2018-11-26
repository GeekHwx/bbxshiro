package com.hwx.bbxshiro.service.impl;

import com.hwx.bbxshiro.dao.UserRepository;
import com.hwx.bbxshiro.entity.User;
import com.hwx.bbxshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HuWanxin
 * @create 2018/11/20 16:29
 * @desc
 **/
@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository userRepository;


	@Override
	public User getUserByName(String name) {
		return userRepository.findByUserName(name);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}
}
