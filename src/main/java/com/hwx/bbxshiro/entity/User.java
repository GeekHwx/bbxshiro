package com.hwx.bbxshiro.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author HuWanxin
 * @create 2018/11/20 16:01
 * @desc
 **/
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_user_shiro", schema = "submission_module", catalog = "")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String viewName;

	private String userName;

	private String userPassword;

	private String perms;//用户权限
	

}
