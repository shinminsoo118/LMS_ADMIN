package com.kpoint.LMS.document.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kpoint.LMS.board.service.BoardService;
import com.kpoint.LMS.board.vo.Board;
import com.kpoint.LMS.common.common.CommandMap;
import com.kpoint.LMS.common.service.CommonService;
import com.kpoint.LMS.document.service.DocumentService;

@Controller
@RequestMapping("/administrator/document/")
public class DocumentController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="documentService")
	private DocumentService documentService;

	@Resource(name="boardService")
	private BoardService boardService;
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	
	private Map<String, Object> boardConfig;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("documentList.do")
	public String documentList(CommandMap commandMap, Model model, Principal principal, Authentication auth){
		
		for(GrantedAuthority gauth : auth.getAuthorities()){
			log.debug("documentList=====================" + gauth.getAuthority());
		}
		
		Map<String, Object> resultMap = documentService.getDocumentList(commandMap.getMap());
		boardConfig = (Map<String, Object>) resultMap.get("boardConfig");
		model.addAttribute("list", resultMap.get("result"));
		model.addAttribute("boardConfig", boardConfig);
		model.addAttribute("pageConfig", resultMap.get("pageConfig"));		
		return "administrator.document.documentList";
	}
	
	@RequestMapping("documentReg.do")
	public String documentReg(Model model,Board boardVo){
//		model.addAttribute("boardVo", boardVo);
//		model.addAttribute("boardConfig", boardConfig);
		return "administrator.document.documentReg";
	}
	
	@RequestMapping("documentInsert.do")
	public String setDocumentInsert(CommandMap commandMap, 
								@ModelAttribute("boardVo") 
								@Valid Board boardVo, 
								BindingResult bindingResult, Model model, 
								Principal principal, HttpServletRequest request)throws Exception{
		if(bindingResult.hasErrors()){
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
	            log.error("error message:" + error.getDefaultMessage());
	        }
			model.addAttribute("boardVo", boardVo);
	        model.addAttribute("boardConfig", boardConfig);
	        return "administrator.document.documentReg";
		}
		
		commandMap.put("WRITER", principal.getName());
		documentService.setDocumentInsert(commandMap.getMap(), request);
//		commonService.setFileInsert(commandMap.getMap(), request);
		
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("boardConfig", boardConfig);
		
		return "redirect:documentList.do";
	}
	
	@RequestMapping("documentDetail.do")
	public String documentDetail(CommandMap commandMap, Model model, Principal principal){
		System.out.println("currentPageNo ----- :" + commandMap.get("currentPageNo"));
		commandMap.put("VIEWUSER", principal.getName());
		Map<String, Object> returnMap = documentService.getDocumentDetail(commandMap.getMap());
		Map<String, Object> resultListMap = documentService.getDocumentList(commandMap.getMap());
		
		model.addAttribute("detail", returnMap.get("detail"));
		model.addAttribute("fileList", returnMap.get("fileList"));
		model.addAttribute("list", resultListMap.get("result"));
		model.addAttribute("boardConfig", boardConfig);
		model.addAttribute("pageConfig", resultListMap.get("pageConfig"));
		
		return "administrator.document.documentDetail";
	}
	
	
	@RequestMapping("documentEdit.do")
	public String documentEdit(CommandMap commandMap){
		return "administrator.document.documentEdit";
	}
	
	//Board 수정부분 불러오기
	@RequestMapping("getDocumentUpdate.do")
	public String getDocumentUpdate(CommandMap commandMap, Model model, Board boardVo){
		
		Map<String, Object> update = documentService.getDocumentUpdate(commandMap.getMap());
		
		model.addAttribute("boardVO", boardVo);
		model.addAttribute("boardConfig", boardConfig);
		model.addAttribute("update", update.get("article"));
		model.addAttribute("fileList", update.get("fileList"));
		
		return "administrator.document.documentEdit";
	}
	
	//Board 수정부분 업데이트
	@RequestMapping("setDocumentUpdate.do")
	public String setDocumentUpdate(CommandMap commandMap, 
								@ModelAttribute("boardVo") 
								@Valid Board boardVo, BindingResult bindingResult, 
								Model model, HttpServletRequest request)throws Exception{
		
		if(bindingResult.hasErrors()){
			List<ObjectError> errors = bindingResult.getAllErrors();
			for(ObjectError error : errors){
				log.error("error message:" + error.getDefaultMessage());
			}
			model.addAttribute("boardVo", boardVo);
			model.addAttribute("bid", commandMap.get("bid"));
			model.addAttribute("SEQ", commandMap.get("SEQ"));
			model.addAttribute("boardConfig", boardConfig);
			//return "administrator.board.Reply";
		}
		
		documentService.setDocumentUpdate(commandMap.getMap(), request);
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("SEQ", commandMap.get("SEQ"));
		model.addAttribute("KEYWORD", commandMap.get("KEYWORD"));
		model.addAttribute("KEYSTRING", commandMap.get("KEYSTRING"));
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		model.addAttribute("boardConfig", boardConfig);
		
		return "redirect:documentDetail.do";
	}
	
	//Board 삭제
	@RequestMapping("Delete.do")
	public String boardDelete(CommandMap commandMap, Model model, HttpServletRequest request){
		System.out.println("BOARDSEQ : " + commandMap.get("BOARDSEQ"));
		System.out.println("GROUPID : " + commandMap.get("GROUPID"));
		documentService.setDocumentDelete(commandMap.getMap(), request);
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("KEYWORD", commandMap.get("KEYWORD"));
		model.addAttribute("KEYSTRING", commandMap.get("KEYSTRING"));
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		return "redirect:documentList.do";
	}
	
	//추천버튼을 클릭했을 경우
	@RequestMapping("Recommandation.do")
	public @ResponseBody Map<String, Object> setRecommandation(CommandMap commandMap, Model model, Principal principal){
		commandMap.put("VIEWUSER", principal.getName());
		int recoCount = boardService.setRecommandation(commandMap.getMap());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("reCount", recoCount);
		
		return resultMap;
	}
	
	//비추천버튼을 클릭했을 경우.
	@RequestMapping("NoRecommandation.do")
	public @ResponseBody Map<String, Object> setNoRecommandation(CommandMap commandMap, Model model, Principal principal){
		commandMap.put("VIEWUSER", principal.getName());
		int noRecoCount = boardService.setNoRecommandation(commandMap.getMap());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("noReCount", noRecoCount);
		return resultMap;
	}	
	
	
	//답글코멘트가 아닌 새로운 코멘트를 넣음.
	@RequestMapping("CommentInsert.do")
	@ResponseBody
	public Map<String, Object> setCommentInsert(CommandMap commandMap, Principal principal) throws UnsupportedEncodingException{
		String COMMENT = URLDecoder.decode((String) commandMap.get("COMMENT"), "utf-8");
		commandMap.put("COMMENT", COMMENT);
		commandMap.put("WRITER", principal.getName());
		boardService.setCommentInsert(commandMap.getMap());
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("bid", commandMap.get("bid"));
		returnMap.put("seq", commandMap.get("BOARDSEQ"));
		return returnMap;
	}
	
	//코멘트 리스트를 JSON형태로 리턴
	@RequestMapping("CommentList.do")
	public @ResponseBody List<Map<String, Object>> getCommentList(CommandMap commandMap, Model model){
		List<Map<String, Object>> comment = boardService.getCommentList(commandMap.getMap());
		return comment;
	}
	
	
	//새로운 코멘트 리플을 넣는 부분
	@RequestMapping("CommentReplyInsert.do")
	@ResponseBody
	public Map<String, Object> setCommentReplyInsert(CommandMap commandMap, Principal principal)throws UnsupportedEncodingException{
		String RECOMMENT = URLDecoder.decode((String) commandMap.get("RECOMMENT"), "utf-8");
		commandMap.put("WRITER", principal.getName());
		commandMap.put("COMMENT", RECOMMENT);
		boardService.setCommentReplyInsert(commandMap.getMap());
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("bid", commandMap.get("bid"));
		returnMap.put("seq", commandMap.get("BOARDSEQ"));
		
		return returnMap;
	}
	
	//코멘트 수정을 위한 내용보기를 JSON형태로 리턴
	@RequestMapping("getCommentView.do")
	public @ResponseBody Map<String, Object> getCommentView(CommandMap commandMap, Model model){
		Map<String, Object> comment = boardService.getCommentView(commandMap.getMap());
		return comment;
	}
	
	//코멘트를 수정을 위한 내용보기를 JSON형태로 리턴
	@RequestMapping("CommentUpdate.do")
	@ResponseBody
	public Map<String, Object> setCommentUpdate(CommandMap commandMap)throws UnsupportedEncodingException{
		String RECOMMENT = URLDecoder.decode((String) commandMap.get("RECOMMENT"), "utf-8");
		commandMap.put("COMMENT", RECOMMENT);
		Map<String, Object> comment = boardService.setCommentUpdate(commandMap.getMap());
		comment.put("seq", commandMap.get("SEQ"));
		
		return comment;
	}
	
	//코멘트 항목을 삭제
	@RequestMapping("CommentDel.do")
	@ResponseBody
	public Map<String, Object> setCommentDelete(CommandMap commandMap)throws UnsupportedEncodingException{
		boardService.setCommentDelete(commandMap.getMap());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("bid", commandMap.get("bid"));
		returnMap.put("bSeq", commandMap.get("BOARDSEQ"));
		
		return returnMap;
	}
	
	@RequestMapping("recordingList.do")
	public String recordingList(CommandMap commandMap, Model model, Principal principal, Authentication auth){
		
		return "administrator.document.recordingList";
	}
	
	@RequestMapping("recordingRegister.do")
	public String recordingRegister(CommandMap commandMap, Model model, Principal principal, Authentication auth){
		
		return "administrator.document.recordingReg";
	}
	
	@RequestMapping("recordingDetail.do")
	public String recordingDetail(CommandMap commandMap, Model model, Principal principal, Authentication auth){
		
		return "administrator.document.recordingDetail";
	}
	
	@RequestMapping("uploadSettingList.do")
	public String uploadSettingList(CommandMap commandMap, Model model, Principal principal, Authentication auth){
		
		return "administrator.document.uploadSettingList";
	}
	
	@RequestMapping("uploadSettingRegister.do")
	public String uploadSettingRegister(CommandMap commandMap, Model model, Principal principal, Authentication auth){
		
		return "administrator.document.uploadSettingReg";
	}
	
	
}