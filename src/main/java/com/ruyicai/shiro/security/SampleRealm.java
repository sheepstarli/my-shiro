package com.ruyicai.shiro.security;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.Md5CredentialsMatcher;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruyicai.shiro.domain.Role;
import com.ruyicai.shiro.domain.User;

public class SampleRealm extends AuthorizingRealm {
	
	private static Logger logger = LoggerFactory.getLogger(SampleRealm.class);
	
	public SampleRealm() {
		setName("SampleRealm");
//		setCredentialsMatcher(new Md5CredentialsMatcher());
	}
	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = token.getUsername();
		logger.info("认证-登录的用户名：" + username);
		if(StringUtils.isBlank(username)) {
			throw new AccountException("用户名为空");
		}
		User user = null;
		try {
			user = User.findUserByUsername(username);
		} catch (Exception e) {
			logger.error("认证用户" + username + "错误", e);
		}
		return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) getAvailablePrincipal(principals);
		logger.info("授权-用户名：" + username);
		User user = User.findUserByUsername(username);
		if(user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for(Role role : user.getRoles()) {
				info.addRole(role.getName());
			}
			return info;
		} else {
			return null;
		}
	}

}
