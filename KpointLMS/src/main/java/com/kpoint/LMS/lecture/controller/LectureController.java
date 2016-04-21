package com.kpoint.LMS.lecture.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kpoint.LMS.common.common.CommandMap;
import com.kpoint.LMS.lecture.service.LectureService;

@Controller
@RequestMapping("/administrator/lecture/")
public class LectureController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="lectureService")
	private LectureService lectureService;
	
	@RequestMapping("lectureList.do")
	public String getLectureList(CommandMap commandMap, Model model){
		
		Map<String, Object> returnMap = lectureService.getSignUpList(commandMap.getMap());
		
		model.addAttribute("boardConfig", returnMap.get("boardConfig"));
		model.addAttribute("pageConfig", returnMap.get("pageConfig"));
		model.addAttribute("boardConfig", returnMap.get("boardConfig"));
		model.addAttribute("clist", returnMap.get("result"));
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("STATUS", commandMap.get("STATUS").toString());
		
		return "administrator.lecture.lectureList";
	}
	
	@RequestMapping("lectureDetail.do")
	public String getLectureDetail(CommandMap commandMap, Model model){
		Map<String, Object> detail = lectureService.getSignDetail(commandMap.getMap());
		model.addAttribute("detail", detail);
		return "administrator.lecture.lectureDetail";
	}
	
	@RequestMapping("priceHistoryList.do")
	public String priceHistoryList(CommandMap commandMap, Model model){
		
		Map<String, Object> returnMap = lectureService.getSignUpList(commandMap.getMap());
		
		model.addAttribute("boardConfig", returnMap.get("boardConfig"));
		model.addAttribute("pageConfig", returnMap.get("pageConfig"));
		model.addAttribute("boardConfig", returnMap.get("boardConfig"));
		model.addAttribute("clist", returnMap.get("result"));
		model.addAttribute("bid", commandMap.get("bid"));
		
		return "administrator.lecture.priceHistoryList";
	}
	
	@RequestMapping("priceHistoryDetail.do")
	public String priceHistoryDetail(CommandMap commandMap, Model model){
		Map<String, Object> detail = lectureService.getSignDetail(commandMap.getMap());
		model.addAttribute("detail", detail);
		return "administrator.lecture.priceHistoryDetail";
	}
	
	@RequestMapping("refundList.do")
	public String refundList(CommandMap commandMap, Model model){
		
		Map<String, Object> returnMap = lectureService.getSignUpList(commandMap.getMap());
		
		model.addAttribute("boardConfig", returnMap.get("boardConfig"));
		model.addAttribute("pageConfig", returnMap.get("pageConfig"));
		model.addAttribute("boardConfig", returnMap.get("boardConfig"));
		model.addAttribute("clist", returnMap.get("result"));
		model.addAttribute("bid", commandMap.get("bid"));
		
		return "administrator.lecture.refundList";
	}
	
	@RequestMapping("refundDetail.do")
	public String refundDetail(CommandMap commandMap, Model model){
		Map<String, Object> detail = lectureService.getSignDetail(commandMap.getMap());
		model.addAttribute("detail", detail);
		return "administrator.lecture.refundDetail";
	}
	
	@RequestMapping("lecturePayment.do")
	public String setLecturePaymentUpdate(CommandMap commandMap, Model model){
		
		int retValue = lectureService.setLecturePaymentUpdate(commandMap.getMap());
		
		model.addAttribute("uStatus", retValue);
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("SEQ", commandMap.get("SEQ"));
		model.addAttribute("STATUS", commandMap.get("STATUS"));
		
		return "redirect:lectureDetail.do";
	}
	
	@RequestMapping("lectureRefindUpdate.do")
	public String setLectureRefindUpdate(CommandMap commandMap, Model model){
		
		int retValue = lectureService.setLectureRefindUpdate(commandMap.getMap());
		
		model.addAttribute("uStatus", retValue);
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("SEQ", commandMap.get("SEQ"));
		model.addAttribute("STATUS", commandMap.get("STATUS"));
		
		return "redirect:lectureDetail.do";
	}
	
	@RequestMapping("lectureSignUpDelete.do")
	public String setLectureSignUpDelete(CommandMap commandMap, Model model){
		
		int retValue = lectureService.setLectureSignUpDelete(commandMap.getMap());
		
		model.addAttribute("dStatus", retValue);
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("SEQ", commandMap.get("SEQ"));
		model.addAttribute("STATUS", commandMap.get("STATUS"));
		
		return "redirect:lectureDetail.do";
	}
	
}