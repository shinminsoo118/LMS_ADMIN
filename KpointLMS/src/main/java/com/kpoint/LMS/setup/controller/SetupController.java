package com.kpoint.LMS.setup.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kpoint.LMS.account.vo.Member;
import com.kpoint.LMS.board.vo.Board;
import com.kpoint.LMS.common.common.CommandMap;
import com.kpoint.LMS.common.util.ShaEncoder;
import com.kpoint.LMS.member.membervo.MemberUpdateVO;
import com.kpoint.LMS.member.service.MemberService;
import com.kpoint.LMS.setup.service.SetupService;

@Controller
@RequestMapping("/administrator/setup/")
public class SetupController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="shaEncoder")
	private ShaEncoder shaEncoder;
	
	@Resource(name="setupService")
	private SetupService setupService;
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	private Map<String, Object> boardConfig;
	
	@RequestMapping("adminList.do")
	public String getAdminList(CommandMap commandMap,Model model){
	
		Iterator<String> iterator = commandMap.getMap().keySet().iterator();
		
		while(iterator.hasNext()){
			String key = iterator.next();
			if(key != null){
				System.out.println("키 : "+ key + ",value : "+commandMap.getMap().get(key));
				System.out.println("null 값");
			}
		}
		
		Map<String,Object> adminList = setupService.getAdminList(commandMap.getMap()); 
		model.addAttribute("userList",adminList.get("result"));
		model.addAttribute("pageConfig", adminList.get("pageConfig"));
		
		
		return "administrator.setup.adminList";
	}
	
	@RequestMapping("adminRegister.do")
	public String adminRegister(CommandMap commandMap){
		return "administrator.setup.adminRegister";
	}
	
	@RequestMapping("adminMemberInsert.do")
	public String setsetupAdminInsert(CommandMap commandMap, 
			@ModelAttribute("memberVo") @Valid Member memberVo, 
			BindingResult bindingResult, Model model, 
			Principal principal, HttpServletRequest request)throws Exception{
		
		if(bindingResult.hasErrors()){
			model.addAttribute("memberVo", memberVo);
			return "front.index";
		}
		
		String goPage = "";
		
		String userId = (String) commandMap.get("USERID");
		String userPw = (String) commandMap.get("USERPW");
		
		String saltPw = shaEncoder.saltEncoding(userPw, userId);
		commandMap.put("USERPW", saltPw);
		
		Object returnType = setupService.setSetupAdminInsert(commandMap.getMap());
//		commonService.setFileInsert(commandMap.getMap(), request);
		
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		model.addAttribute("boardConfig", boardConfig);

//		String goPage = "administrator.setup.adminRegister";
		
		if(returnType == null){
			model.addAttribute("msg", "FAIL");
			goPage = "redirect:adminRegister.do";
		}else{
			model.addAttribute("msg", "SUCCESS");
			goPage = "redirect:adminRegister.do";
		}
		return goPage;
	}
	
	@RequestMapping("memberRoleUpdate.do")
	public @ResponseBody Map<String,Object> memberRoleUpdate(CommandMap commandMap){
		Map<String,Object> myMap = new HashMap<String, Object>();
		System.out.println("memberRoleUpdate 들어옴"+commandMap.getMap().get("userId")+commandMap.getMap().get("userRole"));
		memberService.memberRoleUpdate(commandMap.getMap());
		myMap.put("status", "success");		
		return myMap;
	} // memberEmailCheck.do
	
	@RequestMapping("getMemberEmailCheck.do")
	public @ResponseBody Map<String,Object> setMemberEmailCheck(CommandMap commandMap){
		Map<String,Object> myMap = new HashMap<String, Object>();
		System.out.println("memberRoleUpdate 들어옴"+commandMap.getMap().get("USEREMAIL")+commandMap.getMap().get("userRole"));
		Object emailValue = setupService.getMemberEmailCheck(commandMap.getMap());
		if(Integer.parseInt(emailValue.toString()) > 0){
			myMap.put("status", "fail");
		}else{
			myMap.put("status", "success");	
		}
		return myMap;
	}
	
	@RequestMapping("memberDelete.do")
	public String memberDelete(CommandMap commandMap,Model model, HttpServletRequest request){
		memberService.memberDelete(commandMap.getMap());
		return "redirect:"+request.getHeader("referer");
		
//		return "administrator.setup.adminList";
	}
	
	@RequestMapping("adminDetail.do")
	public String memberDetail(CommandMap commandMap,Model model){
		Map<String,Object> memberDetail = memberService.memberDetail(commandMap.getMap());
		model.addAttribute("result", memberDetail);
		return "administrator.setup.adminDetail";
	}
	
	@RequestMapping("/openMemberUpdate.do")
	public String openMemberUpdate(CommandMap commandMap,Model model){
		Map<String,Object> memberDetail = memberService.memberDetail(commandMap.getMap());
		model.addAttribute("result", memberDetail);
		model.addAttribute("memberUpdateVo",new MemberUpdateVO());
		return "administrator.setup.adminUpdate";
	}
	
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(CommandMap commandMap,Model model,@ModelAttribute("memberUpdateVo") @Valid MemberUpdateVO memberUpdateVo,BindingResult bindingResult,HttpServletRequest request) throws Exception{
		if(bindingResult.hasErrors()){
			System.out.println("에러 발생?");
			
			Map<String,Object> memberDetail = memberService.memberDetail(commandMap.getMap());
			model.addAttribute("result", memberDetail);
			model.addAttribute("memberUpdateVo",memberUpdateVo);
			
			return "administrator.setup.memberUpdate";
			//return "redirect:openMemberUpdate.do";
		}
		
		memberService.memberUpdate(commandMap.getMap(),request);
		Map<String,Object> memberDetail = memberService.memberDetail(commandMap.getMap());
		model.addAttribute("result", memberDetail);
		
		return "redirect:adminDetail.do?&USERID="+request.getParameter("USERID")+"&type="+request.getParameter("type");
	}
	
	/************************************************/
	/*                  상품코드관리                 	*/
	/************************************************/
	@SuppressWarnings("unchecked")
	//상품코드등록관리 페이지 리스트 부분
	@RequestMapping("codeList.do")
	public String productCodeList(CommandMap commandMap, Model model){
		Map<String, Object> resultMap = setupService.getProductList(commandMap.getMap());
		boardConfig = (Map<String, Object>) resultMap.get("boardConfig");
		model.addAttribute("list", resultMap.get("result"));
		model.addAttribute("boardConfig", boardConfig);
		model.addAttribute("pageConfig", resultMap.get("pageConfig"));
		return "administrator.setup.codeList";
	}
	
	//상품코등록관리 페이지 등록 부분
	@RequestMapping("codeRegister.do")
	public String productCodeRegist(CommandMap commandMap){
		return "administrator.setup.codeRegister";
	}
	
	@RequestMapping("codeRegisterInsert.do")
	public String productCodeRegistInsert(CommandMap commandMap, Model model, Principal principal){
		
		String goPage = "";
		
		commandMap.put("WRITER", principal.getName());
		int resval = setupService.setProductCodeInsert(commandMap.getMap());
		
		if(resval > 0){
			model.addAttribute("msg", "SUCCESS");
			goPage = "redirect:codeRegister.do";
		}else{
			model.addAttribute("msg", "SUCCESS");
			goPage = "redirect:codeRegister.do";
		}
			
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		model.addAttribute("boardConfig", boardConfig);
		return goPage;
	}
	
	//상품코등록관리 페이지 수정 부분
	@RequestMapping("codeEdit.do")
	public String productCodeEdit(CommandMap commandMap, Model model){
		//setupService.getProductCode(commandMap.getMap());
		model.addAttribute("boardConfig", boardConfig);
		model.addAttribute("codeInfo", setupService.getProductCode(commandMap.getMap()));
		return "administrator.setup.codeEdit";
	}
	
	@RequestMapping("codeRegisterUpdate.do")
	public String productCodeRegistUpdate(CommandMap commandMap, Model model, Principal principal){
		Map<String, Object> returnMap = setupService.setProductCodeUpdate(commandMap.getMap());
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("CODESEQ", commandMap.get("CODESEQ"));
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		model.addAttribute("KEYWORD", commandMap.get("KEYWORD"));
		model.addAttribute("KEYSTRING", commandMap.get("KEYSTRING"));
		model.addAttribute("message", "update");
		model.addAttribute("codeInfo", returnMap);
		model.addAttribute("boardConfig", boardConfig);
		return "redirect:codeEdit.do";
	}
	
	@RequestMapping("codeDelete.do")
	public String codeDelete(CommandMap commandMap,Model model, HttpServletRequest request){
		setupService.setCodeDelete(commandMap.getMap());
		return "redirect:"+request.getHeader("referer");
		
//		return "administrator.setup.adminList";
	}
	
	/************************************************/
	/*                  결제정보관리                 	*/
	/************************************************/
	@RequestMapping("payment.do")
	public String payMent(CommandMap commandMap){
		return "administrator.setup.payment";
	}
	
	@RequestMapping("boardSetup.do")
	public String boardSetup(CommandMap commandMap, Model model){
		Map<String, Object> resultMap = setupService.getBoardList(commandMap.getMap());
		model.addAttribute("list", resultMap.get("result"));
		model.addAttribute("boardConfig", resultMap.get("boardCongfig"));
		model.addAttribute("pageConfig", resultMap.get("pageConfig"));
		model.addAttribute("userRole", resultMap.get("userRole"));
		return "administrator.setup.boardSetup";
	}
	
	@RequestMapping("boardSetupDetail.do")
	public String boardSetupDetail(CommandMap commandMap, Model model){
		Map<String, Object> resultMap = setupService.getBoardSetupDetail(commandMap.getMap());
		model.addAttribute("detail", resultMap.get("detail"));
		model.addAttribute("tableList", resultMap.get("tableList"));
		model.addAttribute("userRole", resultMap.get("userRole"));
		return "administrator.setup.boardSetupDetail";
	}
	
	@RequestMapping("boardSetupRoleUpdate.do")
	public @ResponseBody Map<String,Object> boardSetupRoleUpdate(CommandMap commandMap, HttpServletRequest request){
		
		Map<String,Object> myMap = new HashMap<String, Object>();
		
		System.out.println("memberRoleUpdate 들어옴"+commandMap.getMap().get("setBoardId")+commandMap.getMap().get("setView"));
		setupService.setBoardSetupRoleUpdate(commandMap.getMap());
		myMap.put("status", "success");		
		return myMap;
	}
	
	@RequestMapping("boardSetupDetailUpdate.do")
	public @ResponseBody Map<String,Object> boardSetupDetailUpdate(CommandMap commandMap){
		Map<String,Object> myMap = new HashMap<String, Object>();
		
		setupService.setBoardSetupDetailUpdate(commandMap.getMap());
		myMap.put("status", "success");		
		return myMap;
	}
	
	@RequestMapping("boardSetupInsert.do")
	public @ResponseBody Map<String,Object> boardSetupInsert(CommandMap commandMap){
		Map<String,Object> myMap = new HashMap<String, Object>();
		
		Object result = setupService.setBoardSetupInsert(commandMap.getMap());
		System.out.println("currentPageNo :" + commandMap.get("currentPageNo"));
		myMap.put("currentPageNo", commandMap.get("currentPageNo").toString());
		if(result == null){
			myMap.put("status", "fail");	
		}else{
			myMap.put("status", "success");	
		}
		return myMap;
	}
	
	@RequestMapping("etcSetup.do")
	public String etcSetup(CommandMap commandMap){
		return "administrator.setup.etcSetup";
	}
	
	@RequestMapping("boardSetupRegister.do")
	public String boardSetupRegister(CommandMap commandMap){
		return "administrator.setup.boardSetupRegister";
	}
	
	@RequestMapping("paymentList.do")
	public String getPayMentList(CommandMap commandMap){
		
		return "administrator.setup.paymentList";
	}
	
	@RequestMapping("paymentDetail.do")
	public String paymentDetail(CommandMap commandMap){
		
		return "administrator.setup.paymentDetail";
	}
	
}