package com.kpoint.LMS.plan;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kpoint.LMS.common.common.CommandMap;

@Controller
@RequestMapping("/administrator/plan/")
public class PlanController {
	Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("planList.do")
	public String planList(CommandMap commandMap){
		return "administrator.plan.planList";
	}
	
	@RequestMapping("planRegister.do")
	public String planRegister(CommandMap commandMap){
		return "administrator.plan.planRegister";
	}
	
}