package com.kpoint.LMS.member.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kpoint.LMS.board.vo.Board;
import com.kpoint.LMS.common.common.CommandMap;
import com.kpoint.LMS.member.mail.EmailSender;
import com.kpoint.LMS.member.membervo.MemberUpdateVO;
import com.kpoint.LMS.member.service.MemberService;
import com.kpoint.LMS.member.sms.SendSMS;

@Controller
/*@RequestMapping("/administrator/member/")*/
public class MemberController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="memberService")
	private MemberService memberService;
	
	private Map<String, Object> boardConfig;
	
	@RequestMapping("/administrator/member/memberList.do")
	public String getMemberList(CommandMap commandMap,Model model){
		
		if(commandMap.getMap().get("type").toString().equals("Student")){
			commandMap.getMap().put("USERROLE", "ROLE_STUDENT");
		}else{
			commandMap.getMap().put("USERROLE", "ROLE_ADMIN");
		}
		
		
		Iterator<String> iterator = commandMap.getMap().keySet().iterator();
		
		while(iterator.hasNext()){
			String key = iterator.next();
			if(key != null){
				System.out.println("키 : "+ key + ",value : "+commandMap.getMap().get(key));
				System.out.println("null 값");
			}
		}
		
		
		Map<String,Object> memberList = memberService.memberList(commandMap.getMap()); 
		model.addAttribute("userList",memberList.get("result"));
		model.addAttribute("pageConfig", memberList.get("pageConfig"));
		
		return "administrator.member.memberList";
	}
	
	@RequestMapping("/administrator/member/memberDelete.do")
	public String memberDelete(CommandMap commandMap,Model model,HttpServletRequest request){
		memberService.memberDelete(commandMap.getMap());
		return "redirect:"+request.getHeader("referer");
	}
	
	@RequestMapping("/administrator/member/memberDetail.do")
	public String memberDetail(CommandMap commandMap,Model model){
		Map<String,Object> memberDetail = memberService.memberDetail(commandMap.getMap());
		model.addAttribute("result", memberDetail);
		return "administrator.member.memberDetail";
	}
	
	@RequestMapping("/administrator/member/openMemberUpdate.do")
	public String openMemberUpdate(CommandMap commandMap,Model model){
		Map<String,Object> memberDetail = memberService.memberDetail(commandMap.getMap());
		model.addAttribute("result", memberDetail);
		model.addAttribute("memberUpdateVo",new MemberUpdateVO());
		return "administrator.member.memberUpdate";
	}
	
	@RequestMapping("/administrator/member/memberUpdate.do")
	public String memberUpdate(CommandMap commandMap,Model model,@ModelAttribute("memberUpdateVo") @Valid MemberUpdateVO memberUpdateVo,BindingResult bindingResult,HttpServletRequest request) throws Exception{
		if(bindingResult.hasErrors()){
			System.out.println("에러 발생?");
			
			Map<String,Object> memberDetail = memberService.memberDetail(commandMap.getMap());
			model.addAttribute("result", memberDetail);
			model.addAttribute("memberUpdateVo",memberUpdateVo);
			
			return "administrator.member.memberUpdate";
			//return "redirect:openMemberUpdate.do";
		}
		/*
		Iterator<String> iterator = commandMap.getMap().keySet().iterator();
		
		while(iterator.hasNext()){
			String key = iterator.next();
			System.out.println("키 : "+ key + ",value : "+commandMap.getMap().get(key));
			System.out.println("null 값");
			
		}
		*/
		//이미지 업로드 처리해야함
		
		memberService.memberUpdate(commandMap.getMap(),request);
		Map<String,Object> memberDetail = memberService.memberDetail(commandMap.getMap());
		model.addAttribute("result", memberDetail);
		
		//return "administrator.member.memberDetail";
		/*System.out.println(request.getHeader("referer").toString()); //이전 모든 주소
		System.out.println(request.getParameterNames().toString()); //파라미터 배열 리턴
		System.out.println(request.getRequestURI()); 파라미터를 제외한 전체 URL
		System.out.println(request.getRequestURL()); 파라미터를 제외한 URL
		System.out.println(request.getParameter("type")); 파라미터 get */
		return "redirect:memberDetail.do?&USERID="+request.getParameter("USERID")+"&type="+request.getParameter("type");
	}
	
	@RequestMapping("/administrator/member/instructorList.do")
	public String instructorList(CommandMap commandMap){
		return "administrator.member.instructorList";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/administrator/member/mailList.do")
	public String mailList(CommandMap commandMap,Model model){
		
		
		Iterator<String> iterator = commandMap.getMap().keySet().iterator();
		
		while(iterator.hasNext()){
			String key = iterator.next();
			if(key != null){
				System.out.println("키 : "+ key + ",value : "+commandMap.getMap().get(key));
				System.out.println("null 값");
			}
		}
		
		
		Map<String,Object> mailList = memberService.getMailList(commandMap.getMap());
		boardConfig = (Map<String, Object>) mailList.get("boardConfig");
		
		log.debug("retVal :" + commandMap.get("retVal"));
		model.addAttribute("userList",mailList.get("result"));
		model.addAttribute("boardConfig", boardConfig);
		model.addAttribute("pageConfig", mailList.get("pageConfig"));
		
		return "administrator.member.mailList";
	}
	
	@RequestMapping("/administrator/member/mailRegi.do")
	public String getMailReg(CommandMap commandMap,Model model){
		
		return "administrator.member.mailReg";
	}
	
	@RequestMapping("/administrator/member/mailSend.do")
	public String setMailSend(CommandMap commandMap, 
								@ModelAttribute("boardVo") 
								@Valid Board boardVo, 
								BindingResult bindingResult, Model model, 
								Principal principal, HttpServletRequest request) throws Exception{
		String goUrl = "redirect:mailList.do";
		log.debug(commandMap.getMap().get("sendTo"));
		String retVal = memberService.setMailSend(commandMap.getMap());
		log.debug("retVal=============" + retVal);
		if(retVal.equals("cancel")){
			goUrl =  "redirect:mailRegi.do";
		}else if(retVal.equals("success")){
			
			goUrl =  "redirect:mailList.do";
		}else{
			goUrl =  "redirect:mailList.do";
		}
		
		model.addAttribute("retVal", retVal);
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("sendTo", commandMap.get("sendTo"));
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		model.addAttribute("boardConfig", boardConfig);
		
		return goUrl;
	}
	
	@RequestMapping("/administrator/member/mailDetail.do")
	public String documentDetail(CommandMap commandMap, Model model, Principal principal){
		System.out.println("currentPageNo ----- :" + commandMap.get("currentPageNo"));
		commandMap.put("VIEWUSER", principal.getName());
		Map<String, Object> returnMap = memberService.getMailDetail(commandMap.getMap());
//		Map<String, Object> resultListMap = memberService.getDocumentList(commandMap.getMap());
		log.debug(commandMap.get("currentPageNo"));
		model.addAttribute("detail", returnMap);
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		model.addAttribute("boardConfig", boardConfig);
		
		return "administrator.member.mailDetail";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/administrator/member/smsList.do")
	public String smsList(CommandMap commandMap,Model model){
		
		Iterator<String> iterator = commandMap.getMap().keySet().iterator();
		
		while(iterator.hasNext()){
			String key = iterator.next();
			if(key != null){
				System.out.println("키 : "+ key + ",value : "+commandMap.getMap().get(key));
				System.out.println("null 값");
			}
		}
		
		
		Map<String,Object> mailList = memberService.getSMSList(commandMap.getMap());
		boardConfig = (Map<String, Object>) mailList.get("boardConfig");
		model.addAttribute("smsList",mailList.get("result"));
		model.addAttribute("boardConfig", boardConfig);
		model.addAttribute("pageConfig", mailList.get("pageConfig"));
		
		return "administrator.member.smsList";
	}
	
	@RequestMapping("/administrator/member/smsRegister.do")
	public String getSmsRegister(CommandMap commandMap,Model model){
//		sendSMS.SendSMS("top118");
		return "administrator.member.smsRegister";
	}
	
	@RequestMapping("/administrator/member/sendSMS.do")
	public String setSendSMS(CommandMap commandMap, 
							@ModelAttribute("boardVo") 
							@Valid Board boardVo, 
							BindingResult bindingResult, Model model, 
							Principal principal, HttpServletRequest request){
		String  goUrl = "";
		goUrl = memberService.setSmsSend(commandMap.getMap());
		log.debug("goUrl sms:" + goUrl);
		if("00".equals(goUrl)){
			
			model.addAttribute("msg", "SUCCESS");
		}if("01".equals(goUrl)){
			
			model.addAttribute("msg", "NOSEND");
		}else if("99".equals(goUrl)){
			model.addAttribute("msg", "NOCACHE");
		}else{	
			if(Integer.parseInt(commandMap.get("doneCount").toString()) > 0){
				model.addAttribute("msg", "SUCCESS");
			}else{
				model.addAttribute("msg", "FAIL");
			}
		}
//		sendSMS.SendSMS(commandMap.getMap());
		model.addAttribute("bid", commandMap.get("bid"));
		if(commandMap.get("doneCount") != null){
			model.addAttribute("doneCount", commandMap.get("doneCount").toString());
		}
		if(commandMap.get("failCount") != null){
			model.addAttribute("failCount", commandMap.get("failCount").toString());
		}
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		model.addAttribute("boardConfig", boardConfig);
		return "redirect:smsRegister.do";
	}
	
	
	@RequestMapping("/administrator/member/smsDetail.do")
	public String getSmsDetail(CommandMap commandMap, Model model, Principal principal){
		System.out.println("currentPageNo ----- :" + commandMap.get("currentPageNo"));
		commandMap.put("VIEWUSER", principal.getName());
		Map<String, Object> returnMap = memberService.getSmsDetail(commandMap.getMap());
//		Map<String, Object> resultListMap = memberService.getDocumentList(commandMap.getMap());
		log.debug(commandMap.get("currentPageNo"));
		model.addAttribute("detail", returnMap);
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		model.addAttribute("boardConfig", boardConfig);
		
		return "administrator.member.smsDetail";
	}
	
	@RequestMapping("/administrator/member/memberRoleUpdate.do")
	public @ResponseBody Map<String,Object> memberRoleUpdate(CommandMap commandMap){
		Map<String,Object> myMap = new HashMap<String, Object>();
		System.out.println("memberRoleUpdate 들어옴"+commandMap.getMap().get("userId")+commandMap.getMap().get("userRole"));
		memberService.memberRoleUpdate(commandMap.getMap());
		myMap.put("status", "success");		
		return myMap;
	}
	
	@RequestMapping("/administrator/member/userMailList")
	@ResponseBody
	public List<Map<String, Object>> userMailList(CommandMap commandMap,Model model){
		List<Map<String, Object>> userList = memberService.getUserMailList(commandMap.getMap());
		
		return userList;
	}
	
	@RequestMapping("/administrator/member/userSmsList")
	@ResponseBody
	public List<Map<String, Object>> getUserSmsList(CommandMap commandMap,Model model){
		List<Map<String, Object>> userList = memberService.getUserSmsList(commandMap.getMap());
		
		return userList;
	}
	
	@RequestMapping("/administrator/member/userGroupList")
	@ResponseBody
	public List<Map<String, Object>> userGroupList(CommandMap commandMap,Model model){
		List<Map<String, Object>> userList = memberService.getUserGroupList(commandMap.getMap());
		
		return userList;
	}
	
}
