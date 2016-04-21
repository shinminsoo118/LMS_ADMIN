package com.kpoint.LMS.statistics;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kpoint.LMS.common.common.CommandMap;

@Controller
@RequestMapping("/administrator/statistics/")
public class StatisticsController {
	Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("connectList.do")
	public String connectList(CommandMap commandMap){
		return "administrator.statistics.connectList";
	} 
	
	@RequestMapping("paymentList.do")
	public String paymentList(CommandMap commandMap){
		return "administrator.statistics.paymentList";
	}
	
	@RequestMapping("searchList.do")
	public String searchList(CommandMap commandMap){
		return "administrator.statistics.searchList";
	}
}