package com.ruyicai.shiro.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/test")
@Controller
public class TestController {
	
	private Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView mav) {
		logger.info("/test/index");
		String message = "";
		try {
			PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
			if(principalCollection != null) {
				logger.info("principalCollection 不为空");
				List principals = principalCollection.asList();
				Map<String, String> attributes = (Map<String, String>) principals.get(1);
				message = "username:" + attributes.get("username") + "  mobileid:" + attributes.get("mobileid") + "  address:" + attributes.get("mobileid") + " rolenames:" + attributes.get("rolenames");
			} else {
				logger.info("principalCollection 空");
			}
			logger.info("message:" + message);
		} catch (Exception e) {
			logger.error("/test/index error", e);
		}
		mav.addObject("message", message);
		mav.setViewName("showMessage");
		return mav;
	}

}
