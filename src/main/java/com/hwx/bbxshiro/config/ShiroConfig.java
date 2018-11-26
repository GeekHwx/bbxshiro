package com.hwx.bbxshiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author HuWanxin
 * @create 2018/11/22 15:20
 * @desc shiro配置类
 **/
@Configuration
public class ShiroConfig {

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	// 下面两个方法对 注解权限起作用有很大的关系，请把这两个方法，放在配置的最上面
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
		defaultAAP.setProxyTargetClass(true);
		return defaultAAP;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	//shiro内置过滤器
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		bean.setLoginUrl("/toLogin");
		bean.setSuccessUrl("/toIndex");
		bean.setUnauthorizedUrl("/noAuth");
		/**
		 * shiro内置过滤器，可以实现权限相关的拦截
		 * 常用如下：
		 * anon		匿名使用，无需认证即可访问
		 * authc	需要认证(登录)才能使用
		 * roles	表示用户必需已通过认证，并拥有某种角色才可以访问
		 * loginUrl 		未登录的请求自动跳转到登录页面，不是必须的属性，不输入地址的话会自动寻找项目web项目的根目录下的”/login.jsp”页面,可自定义登录页的路径
		 * successUrl		登录成功默认跳转页面，不配置则跳转至”/”。如果登陆前点击的一个需要登录的页面，则在登录自动跳转到那个需要登录的页面。不跳转到此
		 * unauthorizedUrl	没有权限默认跳转的页面
		 */
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("/toLogin", "anon");
		map.put("/loginUser", "anon");
		map.put("/noAuth", "anon");
		map.put("/static/*", "anon"); //匿名访问静态资源
		map.put("/add", "authc");//需认证才可以访问
		map.put("/update", "authc");
		map.put("/logout", "authc");
		map.put("/toIndex", "authc");
		map.put("/*", "authc");
		map.put("/**", "authc");
		map.put("/*.*", "authc");
		bean.setFilterChainDefinitionMap(map);
		return bean;
	}

	//配置核心安全事务管理器
	@Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(userRealm);//设置realm
		//用户授权/认证信息Cache, 采用EhCache缓存
//		manager.setCacheManager(getEhCacheManager());
		//注入记住我管理器
		manager.setRememberMeManager(rememberMeManager());
		return manager;
	}

	//自定义权限和授权配置
	@Bean(name = "userRealm")
	public UserRealm getUserRealm() {
		return new UserRealm();
	}


	//配置thymeleaf与shiro页面标签整合使用
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	/**
	 * cookie对象;
	 * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
	 *
	 * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		//这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		//记住我cookie生效时间3天,单位秒
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}

	/**
	 * cookie管理对象;
	 * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
	 *
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		//rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
		cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
		return cookieRememberMeManager;
	}


}
