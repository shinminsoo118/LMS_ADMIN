package com.kpoint.LMS.document.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface DocumentService {
	
	/************************************************************************/	
	/*							Document									*/
	/************************************************************************/
	Map<String, Object> getDocumentList(Map<String, Object> map);
	
	//게시판 내용을 입력함.
	void setDocumentInsert(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	Map<String, Object> getDocumentDetail(Map<String, Object> map);
	
	//게시판 내용을 수정하기 위해 글 내용을 가져오는 것
	Map<String, Object> getDocumentUpdate(Map<String, Object> map);
	
	//게시판 내용을 수정함
	void setDocumentUpdate(Map<String, Object> map,HttpServletRequest request)throws Exception;
	
	//게시판 항목을 삭제함
	void setDocumentDelete(Map<String, Object> map, HttpServletRequest request);
	
}
