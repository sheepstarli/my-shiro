package com.ruyicai.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {
	
	private Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(value = "username", required = false) String username,
	@RequestParam(value = "password", required = false) String password,
	ModelAndView mav) {
		logger.info("login username:{} password:{}", new Object[] {username, password});
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new  UsernamePasswordToken(username, password);
		currentUser.login(token);
		mav.setViewName("index");
		return mav;
	}

}
