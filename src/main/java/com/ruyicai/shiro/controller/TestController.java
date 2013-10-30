package com.ruyicai.shiro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/test")
@Controller
public class TestController {
	
	private Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView mav) {
		logger.info("/test/index");
		mav.addObject("message", "这里是主页");
		mav.setViewName("showMessage");
		return mav;
	}

}
