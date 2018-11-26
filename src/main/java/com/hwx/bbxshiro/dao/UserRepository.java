package com.hwx.bbxshiro.dao;

import com.hwx.bbxshiro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author HuWanxin
 * @create 2018/11/20 16:26
 * @desc
 **/
public interface UserRepository extends JpaRepository<User,Long> {

	User findByUserName(String name);

//	@Query("select u from User u where u.userName=?1 and u.userPassword=?2")
//	User checkLoginInfo(String userName, String userPassword);

}
