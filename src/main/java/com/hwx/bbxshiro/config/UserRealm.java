package com.hwx.bbxshiro.config;

import com.hwx.bbxshiro.entity.User;
import com.hwx.bbxshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author HuWanxin
 * @create 2018/11/22 15:20
 * @desc
 **/
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 授权
	 *
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//添加资源的授权字符串
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		//实际开发是查询当前用户的所属角色、和权限集合
		User dbUser = userService.findById(user.getId());
		//配置所属权限
		info.addStringPermission(dbUser.getPerms());
		//配置所属角色
		// info.setRoles(roles);

		return info;
	}

	/**
	 * 认证
	 *
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String tokenUsername = token.getUsername();
		String tokenUserPassword = String.valueOf(token.getPassword());
		User user = userService.getUserByName(tokenUsername);
		if(user==null){
			return null;//若用户不存在，直接返回null，shiro底层会抛出UnknownAccountException异常
		}else{
			session.setAttribute("user",user);
		}

		/**
		 * 校验密码是否正确,共三个参数,失败会返回
		 * 1、返回给subject.login(token);的信息
		 * 2、查询出对象的密码
		 * 3、shiro的名字
		 */
		return new SimpleAuthenticationInfo(user, user.getUserPassword(), getName());
	}


}
