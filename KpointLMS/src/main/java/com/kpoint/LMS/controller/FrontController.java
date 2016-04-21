package com.kpoint.LMS.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kpoint.LMS.account.vo.Member;

@Controller
public class FrontController {
	Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("/index.do")
	public String front(Model model){
		model.addAttribute("memberVo", new Member());
		return "front.index";
	}
}
