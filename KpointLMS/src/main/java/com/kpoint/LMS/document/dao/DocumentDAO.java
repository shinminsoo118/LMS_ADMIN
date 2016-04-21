package com.kpoint.LMS.document.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.kpoint.LMS.common.dao.AbstractDAO;

@Repository("documentDAO")
public class DocumentDAO extends AbstractDAO{
	//게시판 전체글 목록개수를 가져오는 부분
	String firstName = "document";
	
	public Integer getTotalList(Map<String, Object> map) {
		return (Integer) selectOne(firstName + ".getTotalList", map);
	}
	
	//게시판 목록을 가져오는 부분
	@SuppressWarnings("unchecked")
	public Map<String, Object> getDocumentList(Map<String, Object> map) {
		return (Map<String, Object>)selectPagingList(firstName + ".getDocumentList", map);
	}
	
	public void setDocumentInsert(Map<String, Object> map ){
		int boardId = (Integer) insert(firstName + ".setDocumentInsert", map); 
		log.debug("boardId seq......" + boardId);
	}
	
	//현제페이지를 처음으로 보았을때 데이터입력
	@SuppressWarnings("unchecked")
	public Map<String, Object> getDocumentViewCount(Map<String, Object> map){
		return (Map<String, Object>) selectOne(firstName + ".getDocumentViewCount", map);
	}
	
	//현재페이지를 처음으로 보았는지 판단
	public void setDocumentViewInsert(Map<String, Object> map){
		insert(firstName + ".setDocumentViewInsert", map);
	}
	
	//게시판 HIT를 업데이트
	public void setDocumentHitUpdate(Map<String, Object> map){
		update(firstName + ".setDocumentHitUpdate", map);
	}
	
	//게시판 상세내용을 가져오는 부분
	@SuppressWarnings("unchecked")
	public Map<String, Object> getDocumentDetail(Map<String, Object> map){
		return (Map<String, Object>) selectOne(firstName + ".getDocumentDetail", map);
	}
	
	//게시판 상세내용을 가져오는 부분
	@SuppressWarnings("unchecked")
	public Map<String, Object> getDocumentUpdate(Map<String, Object> map){
		return (Map<String, Object>) selectOne(firstName + ".getDocumentDetail", map);
	}
	
	//게시판을 수정 엡디읕 하는부분
	public void setDocumentUpdate(Map<String, Object> map){
		update(firstName + ".setDocumentUpdate", map);
	}
	
	public Object getDocumentOrderIndex(Map<String, Object> map){
		return selectOne(firstName + ".getDocumentOrderIndex", map);
		
	}
	
	public void setDocumentDelete(Map<String, Object> map){
		delete(firstName + ".setDocumentDelete", map);
	}
	
	public void setDocumentDeleteOfOrderIndex(Map<String, Object> map){
		delete(firstName + ".setDocumentDeleteOfOrderIndex", map);
	}
	
	public void setDocCommentAll(Map<String, Object> map){
		delete(firstName + ".setDocCommentAll", map);
	}
	
}
