package com.kpoint.LMS.board.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpoint.LMS.board.service.BoardService;
import com.kpoint.LMS.board.vo.Board;
import com.kpoint.LMS.common.common.CommandMap;

@Controller
@RequestMapping("/administrator/board/*")
public class BoardController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	private Map<String, Object> boardConfig;
	
	//Board 리스트부분
	@SuppressWarnings("unchecked")
	@RequestMapping("List.do")
	public String getBoardList(CommandMap commandMap, Model model, Principal principal) throws UnsupportedEncodingException{
		
		//검색어를 한글로 입력했을때 처리
		/*if(commandMap.get("KEYSTRING") != null){
			String KEYSTRING;
			KEYSTRING = (String) commandMap.get("KEYSTRING");
			KEYSTRING = new String(KEYSTRING.getBytes("ISO-8859-1"),"UTF-8");
			commandMap.put("KEYSTRING", KEYSTRING);
		}*/
		Map<String, Object> resultMap = boardService.getBoardList(commandMap.getMap());
		boardConfig = (Map<String, Object>) resultMap.get("boardConfig");
		model.addAttribute("list", resultMap.get("result"));
		model.addAttribute("boardConfig", boardConfig);
		model.addAttribute("pageConfig", resultMap.get("pageConfig"));
		return "administrator.board.List";
	}
	
	//Board 상세화면 부분
	@RequestMapping("Detail.do")
	public String getBoardDetail(CommandMap commandMap, Model model, Principal principal){
		commandMap.put("VIEWUSER", principal.getName());
		Map<String, Object> returnMap = boardService.getBoardDetail(commandMap.getMap());
		Map<String, Object> resultListMap = boardService.getBoardList(commandMap.getMap());
		model.addAttribute("detail", returnMap.get("detail")); //글상세부분을 가져오는 부분
		model.addAttribute("fileList", returnMap.get("fileList"));
		model.addAttribute("list", resultListMap.get("result"));
		model.addAttribute("boardConfig", boardConfig);
		model.addAttribute("pageConfig", resultListMap.get("pageConfig"));
		return "administrator.board.Detail";
	}
	
	//Board 새글 등록화면
	@RequestMapping("Reg.do")
	public String boardWrite(Model model, Board boardVo){
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("boardConfig", boardConfig);
		return "administrator.board.Reg";
	}
	
	//Board 새글 등록부분
	@RequestMapping("Insert.do")
	public String setBoardInsert(CommandMap commandMap, @ModelAttribute("boardVo") @Valid Board boardVo, BindingResult bindingResult, Model model, Principal principal, HttpServletRequest request) throws Exception{
		
		if(bindingResult.hasErrors()){
			List<ObjectError> errors= bindingResult.getAllErrors();
	        for (ObjectError error : errors) {
	            log.error("error message:" + error.getDefaultMessage());
	        }
	        model.addAttribute("boardVo", boardVo);
	        model.addAttribute("boardConfig", boardConfig);
	        return "administrator.board.Reg";
		}
		
		commandMap.put("WRITER", principal.getName());
		boardService.setBoardInsert(commandMap.getMap(), request);
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("boardConfig", boardConfig);
		return "redirect:List.do";
	}
	
	//Board 답글화면
	@RequestMapping("Reply.do")
	public String setBoardReply(Model model, Board boardVo){
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("boardConfig", boardConfig);
		return "administrator.board.Reply";
	}
		
	//Board 답글등록 부분
	@RequestMapping("ReplyInsert.do")
	public String setBoardReplyInsert(CommandMap commandMap, @ModelAttribute("boardVo") @Valid Board boardVo, BindingResult bindingResult, Model model, Principal principal){
		if(bindingResult.hasErrors()){
			List<ObjectError> errors = bindingResult.getAllErrors();
	        for (ObjectError error : errors) {
	            log.error("error message:" + error.getDefaultMessage());
	        }
	        model.addAttribute("boardVo", boardVo);
	        model.addAttribute("bid", commandMap.get("bid"));
	        model.addAttribute("GROUPID", commandMap.get("GROUPID"));
	        model.addAttribute("DEPTH", commandMap.get("DEPTH"));
	        model.addAttribute("ORDERINDEX", commandMap.get("ORDERINDEX"));
	        model.addAttribute("boardConfig", boardConfig);
	        return "administrator.board.Reply";
		}
		commandMap.put("WRITER", principal.getName());
		boardService.setBoardReplyInsert(commandMap.getMap());
		model.addAttribute("bid", commandMap.get("bid"));
		return "redirect:List.do";
	}
	
	//추천버튼을 클릭했을 경우.
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
	
	//Board 수정부분 불러오기
	@RequestMapping("getUpdate.do")
	public String getBoardUpdate(CommandMap commandMap, Model model, Board boardVo){
		Map<String, Object> update = boardService.getBoardUpdate(commandMap.getMap());
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("boardConfig", boardConfig);
		model.addAttribute("update", update.get("article"));
		model.addAttribute("fileList", update.get("fileList")); //첨부파일 목록 
		return "administrator.board.Edit";
	}
	
	//Board 수정부분 업데이트
	@RequestMapping("setUpdate.do")
	public String setBoardUpdate(CommandMap commandMap, @ModelAttribute("boardVo") @Valid Board boardVo, BindingResult bindingResult, Model model, HttpServletRequest request) throws Exception{
		
		if(bindingResult.hasErrors()){
			List<ObjectError> errors = bindingResult.getAllErrors();
	        for (ObjectError error : errors) {
	            log.error("error message:" + error.getDefaultMessage());
	        }
	        model.addAttribute("boardVo", boardVo);
	        model.addAttribute("bid", commandMap.get("bid"));
	        model.addAttribute("SEQ", commandMap.get("SEQ"));
	        model.addAttribute("boardConfig", boardConfig);
	        return "administrator.board.Reply";
		}
		boardService.setBoardUpdate(commandMap.getMap(), request);
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("SEQ", commandMap.get("SEQ"));
		model.addAttribute("KEYWORD", commandMap.get("KEYWORD"));
		model.addAttribute("KEYSTRING", commandMap.get("KEYSTRING"));
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		model.addAttribute("boardConfig", boardConfig);
		return "redirect:Detail.do";
	}
	
	//Board 삭제
	@RequestMapping("Delete.do")
	public String boardDelete(CommandMap commandMap, Model model, HttpServletRequest request){
		log.debug("Delete.do=========================");
		boardService.setBoardDelete(commandMap.getMap(), request);
		model.addAttribute("bid", commandMap.get("bid"));
		model.addAttribute("KEYWORD", commandMap.get("KEYWORD"));
		model.addAttribute("KEYSTRING", commandMap.get("KEYSTRING"));
		model.addAttribute("currentPageNo", commandMap.get("currentPageNo"));
		return "redirect:List.do";
	}
	
	
	@RequestMapping("FileDelete.do")
	@ResponseBody
	public void setFileDelete(CommandMap commandMap, HttpServletRequest request) throws UnsupportedEncodingException{
		boardService.setFileDelete(commandMap.getMap(), request);
	}
	
	/*//파일 업로드 
	@RequestMapping("FileInsert.do")
	@ResponseBody
	public Map<String, Object> setFileInsert(CommandMap commandMap, HttpServletRequest request) throws Exception{
		System.out.println("파일업로드 Controller");
		Map<String, Object> fileList = boardService.setFileInsert(commandMap.getMap(), request);
		return fileList;
	}*/
	
	//코멘트 리스트를 JSON형태로 리턴
	@RequestMapping("CommentList.do")
	public @ResponseBody List<Map<String, Object>> getCommentList(CommandMap commandMap, Model model){
		List<Map<String, Object>> comment = boardService.getCommentList(commandMap.getMap());
		return comment;
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
	
	//새로운 코멘트 리플을 넣는 부분
	@RequestMapping("CommentReplyInsert.do")
	@ResponseBody
	public Map<String, Object> setCommentReplyInsert(CommandMap commandMap, Principal principal) throws UnsupportedEncodingException{
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
		System.out.println("comment = "+comment);
		return comment;
	}
	
	//코멘트를 수정 업데이트 하는 부분
	@RequestMapping("CommentUpdate.do")
	@ResponseBody
	public Map<String, Object> setCommentUpdate(CommandMap commandMap) throws UnsupportedEncodingException{
		String RECOMMENT = URLDecoder.decode((String) commandMap.get("RECOMMENT"), "utf-8");
		commandMap.put("COMMENT", RECOMMENT);
		Map<String, Object> comment = boardService.setCommentUpdate(commandMap.getMap());
		comment.put("seq", commandMap.get("SEQ"));
		return comment;
	}
	
	//코멘트 항목을 삭제.
	@RequestMapping("CommentDel.do")
	@ResponseBody
	public Map<String, Object> setCommentDelete(CommandMap commandMap) throws UnsupportedEncodingException{
		boardService.setCommentDelete(commandMap.getMap());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("bid", commandMap.get("bid"));
		returnMap.put("bSeq", commandMap.get("BOARDSEQ"));
		return returnMap;
	}
	
	//Exception이 발생하였을때 처리
	@ExceptionHandler(Exception.class)
	public ModelAndView HandlerException(Exception ex){
		ModelAndView mv = null;
		if(ex.getMessage().equals("noBoard")){
			mv = new ModelAndView("administrator.board.exception");
			mv.addObject("message", "존재하지 않는 게시판입니다.");
		}
		return mv;
	}
}
