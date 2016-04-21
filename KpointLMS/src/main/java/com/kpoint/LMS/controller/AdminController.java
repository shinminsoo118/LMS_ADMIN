package com.kpoint.LMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrator/*")
public class AdminController {
	
	@RequestMapping("index.do")
	public String documentSetup(){
		return "admin.index";
	}
}
