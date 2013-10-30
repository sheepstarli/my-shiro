package com.ruyicai.shiro.security;

import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnyRolesAuthorizationFilter extends AuthorizationFilter {
	
	private Logger logger = LoggerFactory.getLogger(AnyRolesAuthorizationFilter.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest paramServletRequest,
			ServletResponse paramServletResponse, Object paramObject)
			throws Exception {
		Subject subject = getSubject(paramServletRequest, paramServletResponse);
		String[] rolesArray = (String[]) paramObject;
		for(String role : rolesArray) {
			logger.info(role);
		}
		if(rolesArray == null || rolesArray.length == 0) {
			return true;
		}
		
		Set<String> roles = CollectionUtils.asSet(rolesArray);
		for(String role : roles) {
			if(subject.hasRole(role)) {
				return true;
			}
		}
		return false;
	}

}
