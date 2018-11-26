//package com.hwx.bbxshiro.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.*;
//import java.util.List;
//
///**
// * @author HuWanxin
// * @create 2018/11/22 9:15
// * @desc
// **/
//@Getter
//@Setter
//@ToString
//@Entity
//@Table(name = "t_role", schema = "submission_module", catalog = "")
//public class Role {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//
//	private String roleName;
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	private User user;
//
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "role")
//	private List<Permission> permissions;
//
//
//}
