package com.kpoint.LMS.curriculumn.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kpoint.LMS.common.common.CommandMap;
import com.kpoint.LMS.common.service.CommonService;
import com.kpoint.LMS.curriculumn.service.CurriculumnService;

@Controller
@RequestMapping("/administrator/curriculumn/")
public class CurriculumnController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="curriculumnService")
	private CurriculumnService curriculumnService;
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@RequestMapping("curriculumnList.do")
	public String getCurriculumnList(CommandMap commandMap, Model model){
		Map<String, Object> returnMap = curriculumnService.getCurriculumnList(commandMap.getMap());
		model.addAttribute("boardConfig", returnMap.get("boardConfig"));
		model.addAttribute("pageConfig", returnMap.get("pageConfig"));
		model.addAttribute("boardConfig", returnMap.get("boardConfig"));
		model.addAttribute("clist", returnMap.get("result"));
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("CURSTATUS", commandMap.get("CURSTATUS"));
		return "administrator.curriculumn.curriculumnList";
	}
	
	//교육과정 목록을 가져오는 부분.
	@RequestMapping("productList.do")
	@ResponseBody
	public Map<String, Object> getProductCodeList(CommandMap commandMap){
		List<Map<String, Object>> codeList = curriculumnService.getProductCodeList(commandMap.getMap());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("codeList", codeList);
		returnMap.put("mode", commandMap.get("mode"));
		returnMap.put("procode", commandMap.get("procode"));
		return returnMap;
	}
	
	//교육과정 상세정보를 가져오는 부분.
	@RequestMapping("ProductDetailInfo.do")
	@ResponseBody
	public Map<String, Object> getProductDetailInfo(CommandMap commandMap){
		String SEQ = (String) commandMap.get("CODESEQ");
		if(SEQ.equals("")){
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("message","error");
			return returnMap;
		}
		Map<String, Object> productDetail = curriculumnService.getProductDetailInfo(commandMap.getMap());
		productDetail.put("mode", commandMap.get("mode"));
		return productDetail;
	}
	
	@RequestMapping("curriculumnRegister.do")
	public String getCurriculumnRegister(CommandMap commandMap, Model model){
		//Map<String, Object> returnMap = curriculumnService.getCurriculumnRegister(commandMap.getMap());
		//model.addAttribute("curriculumn", returnMap);
		//model.addAttribute("boardConfig", boardConfig);
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		
		return "administrator.curriculumn.curriculumnRegister";
	}
	
	@RequestMapping("curriculumnInsert.do")
	public String setCurriculumnInsert(CommandMap commandMap, Model model, Principal principal){
		/*int PROCODE = Integer.parseInt(commandMap.get("PROCODE").toString());
		commandMap.put("PROCODE", PROCODE);*/
		commandMap.put("WRITER", principal.getName());
		curriculumnService.setCurriculumnInsert(commandMap.getMap());
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("CURSEQ", commandMap.get("CURSEQ"));
		model.addAttribute("KEYWORD", commandMap.get("KEYWORD"));
		model.addAttribute("KEYSTRING", commandMap.get("KEYSTRING"));
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		model.addAttribute("CURSTATUS", commandMap.get("CURSTATUS"));
		return "redirect:curriculumnList.do";
	}
	
	@RequestMapping("curriculumnDetail.do")
	public String getCurriculumnDetail(CommandMap commandMap, Model model){
		Map<String, Object> detail = curriculumnService.getCurriculumnDetail(commandMap.getMap());
		model.addAttribute("detail", detail);
//		model.addAttribute("bid", commandMap.get("bid"));
//		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		return "administrator.curriculumn.curriculumnDetail";
	}
	
	@RequestMapping("curriculumnEdit.do")
	public String getCurriculumnEdit(CommandMap commandMap, Model model){
		Map<String, Object> detail = curriculumnService.getCurriculumnDetail(commandMap.getMap());
		model.addAttribute("detail", detail);
//		model.addAttribute("bid", commandMap.get("bid"));
//		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		return "administrator.curriculumn.curriculumnEdit";
	}

	@RequestMapping("curriculumnUpdate.do")
	public String setCurriculumnUpdate(CommandMap commandMap, Model model, Principal principal){
		commandMap.put("WRITER", principal.getName());
		curriculumnService.setCurriculumnUpdate(commandMap.getMap());
		
		model.addAttribute("bid",commandMap.get("bid"));
		model.addAttribute("CURSEQ",commandMap.get("CURSEQ"));
		model.addAttribute("KEYWORD",commandMap.get("KEYWORD"));
		model.addAttribute("KEYSTRING",commandMap.get("KEYSTRING"));
		model.addAttribute("currentPageNo",commandMap.get("currentPageNo"));
		model.addAttribute("CURSTATUS",commandMap.get("CURSTATUS"));
		model.addAttribute("resStatus","curriculumnUpdate");
		return "redirect:curriculumnList.do";
	}
	
	@RequestMapping("curriculumnDelete.do")
	public String setCurriculumnDelete(CommandMap commandMap, Model model, Principal principal, HttpServletRequest request){
		commandMap.put("WRITER", principal.getName());
		curriculumnService.setCurriculumnDelete(commandMap.getMap(), request);
		System.out.println("CURSTATUS" + commandMap.get("CURSTATUS"));
		model.addAttribute("bid",commandMap.get("bid"));
		model.addAttribute("SEQ",commandMap.get("SEQ"));
		model.addAttribute("KEYWORD",commandMap.get("KEYWORD"));
		model.addAttribute("KEYSTRING",commandMap.get("KEYSTRING"));
		model.addAttribute("CURSTATUS",commandMap.get("CURSTATUS"));
		model.addAttribute("currentPageNo",commandMap.get("currentPageNo"));
		model.addAttribute("resStatus","curriculumnDelete");
		return "redirect:curriculumnList.do";
	}
	
	@RequestMapping("courseRegister.do")
	public String getCousseRegister(CommandMap commandMap, Model model){
		Map<String, Object> detail = curriculumnService.getCurriculumnDetail(commandMap.getMap());
		model.addAttribute("detail", detail);
		model.addAttribute("bid",commandMap.get("bid"));
		model.addAttribute("SEQ",commandMap.get("SEQ"));
		model.addAttribute("KEYWORD",commandMap.get("KEYWORD"));
		model.addAttribute("KEYSTRING",commandMap.get("KEYSTRING"));
		model.addAttribute("CURSTATUS",commandMap.get("CURSTATUS"));
		model.addAttribute("currentPageNo",commandMap.get("currentPageNo"));
		return "administrator.curriculumn.courseRegister";
	}
	
	@RequestMapping("courseInsert.do")
	public String setCourseRegInsert(CommandMap commandMap, Model model, Principal principal){
		
		String goUrl = "";
		String returnType = curriculumnService.setCourseRegInsert(commandMap.getMap());
		
		if("success".equals(returnType)){
			goUrl = "redirect:courseRegister.do";
		}else{
			goUrl = "redirect:courseRegister.do";
		}
		
		model.addAttribute("bid",commandMap.get("bid"));
		model.addAttribute("SEQ",commandMap.get("CURSEQ"));
		model.addAttribute("CURSTATUS",commandMap.get("CURSTATUS"));
		model.addAttribute("currentPageNo",commandMap.get("currentPageNo"));
		model.addAttribute("resStatus",returnType);
		
		return goUrl;
	}
	
}