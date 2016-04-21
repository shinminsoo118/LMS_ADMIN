package com.kpoint.LMS.board.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface BoardService {
	
	/************************************************************************/	
	/*							게시판 부분   								*/
	/************************************************************************/
	//게시판 전체목록수를 가져옴.
	/*Map<String, Object> getTotalList(Map<String, Object> map);*/
	
	//게시판 목록리스트를 가져옴.
	Map<String, Object> getBoardList(Map<String, Object> map);
	
	//게시판 상세내용을 가져옴.
	Map<String, Object> getBoardDetail(Map<String, Object> map);
	
	//게시판 내용을 입력함.
	void setBoardInsert(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	//게시판 새답글내용을 입력함.
	void setBoardReplyInsert(Map<String, Object> map);
	
	//게시판 내용을 수정하기 위해 글 내용을 가져옴..
	Map<String, Object> getBoardUpdate(Map<String, Object> map);
	
	//게시판 내용을 수정함.
	void setBoardUpdate(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	//게시판 항목을 삭제함.
	void setBoardDelete(Map<String, Object> map, HttpServletRequest request);
	
	//게시판 파일을 지움.
	void setFileDelete(Map<String, Object> map, HttpServletRequest request);
	
	int setRecommandation(Map<String, Object> map);

	int setNoRecommandation(Map<String, Object> map);
	
	/************************************************************************/	
	/*							코멘트 관련 부분								*/
	/************************************************************************/
	//코멘트 리스트를 가져옴.
	List<Map<String, Object>> getCommentList(Map<String, Object> map);
	
	//새코멘트를 입력함.
	void setCommentInsert(Map<String, Object> map);
	
	//새 답글 코멘트를 입력함.
	void setCommentReplyInsert(Map<String, Object> map);
	
	//수정을 위해 코멘트 글을 가져옴.
	Map<String, Object> getCommentView(Map<String, Object> map);
		
	//코멘트를 수정함.
	Map<String, Object> setCommentUpdate(Map<String, Object> map);
	
	//코멘트를 삭제함.
	void setCommentDelete(Map<String, Object> map);

}
