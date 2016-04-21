package com.kpoint.LMS.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kpoint.LMS.common.dao.AbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends AbstractDAO{
	//게시판 전체글 목록개수를 가져오는 부분
	public Integer getTotalList(Map<String, Object> map) {
		return (Integer) selectOne("board.getTotalList", map);
	}
	
	//게시판 목록을 가져오는 부분
	@SuppressWarnings("unchecked")
	public Map<String, Object> getBoardList(Map<String, Object> map) {
		return (Map<String, Object>)selectPagingList("board.getBoardList", map);
	}
	
	//게시판 설정파일을 가져오는 부분
	@SuppressWarnings("unchecked")
	public Map<String, Object> boardConfig(Map<String, Object> map){
		Map<String, Object> configMap = (Map<String, Object>)getBoardConfig("board.getBoardConfig", map);
		return configMap;
	}
	
	//게시판 상세내용을 가져오는 부분
	@SuppressWarnings("unchecked")
	public Map<String, Object> getBoardDetail(Map<String, Object> map) {
		return (Map<String, Object>)selectOne("board.getBoardDetail",map);
	}

	//게시판을 등록하는 부분
	public void setBoardInsert(Map<String, Object> map) {
		insert("board.setBoardInsert", map); 
	}
	
	//게시판수정을 위해 글을 가져오는 부분
	@SuppressWarnings("unchecked")
	public Map<String, Object> getBoardUpdate(Map<String, Object> map) {
		return (Map<String, Object>)selectOne("board.getBoardDetail",map);
	}

	//게시판을 수정업데이트 하는 부분
	public void setBoardUpdate(Map<String, Object> map) {
		update("board.setBoardUpdate", map);
	}

	//게시판 항목을 삭제하는 부분
	public void setBoardDelete(Map<String, Object> map) {
		delete("board.setBoardDelete", map);
	}
	
	//게시판 Hit를 업데이트 하는 부분
	public void setBoardHitUpdate(Map<String, Object> map){
		update("board.setBoardHitUpdate", map);
	}
	
	//답글을 새로 등록함.
	public void setBoardReplyInsert(Map<String, Object> map) {
		insert("board.setBoardReplyInsert", map);
	}
	
	// 게시판의 그룹별 답글의 개수나 null을 리턴
	public Object getBoardOrderIndex(Map<String, Object> map) {
		return selectOne("board.getBoardOrderIndex", map);
	}
	
	//삽이하려는 곳보다 큰 orderIndex을 업데이트
	public void setBoardReplyUpdateIndex(Map<String, Object> map) {
		update("board.setBoardReplyUpdateIndex", map);
	}
	
	//답글 중간에 새글을 삽입
	public void setBoardReplyInsertIndex(Map<String, Object> map) {
		update("board.setBoardReplyInsertIndex", map);
	}
	
	//Board의 코멘트수를 업데이트 함.
	public void setBoardCommentCountUpdate(Map<String, Object> map) {
		update("board.setBoardCommentCountUpdate", map);
	}
	
	//코멘트 리스트를 가져오는 부분
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCommentList(Map<String, Object> map){
		return (List<Map<String, Object>>)selectList("board.getCommentList", map);
	}
	
	//답글 코멘트가 아닌 새코맨를 넣는 부분
	public void setCommentInsert(Map<String, Object> map){
		insert("board.setCommentInsert", map);
	}
	
	// 코멘트의 그룹별 답글의 개수나 null 값을 리턴
	public Object getCommentOrderIndex(Map<String, Object> map) {
		return selectOne("board.getCommentOrderIndex", map);
	}
	
	//코멘트 답글을 맨 밑에 넣는 부분
	public void setCommentReplyInsert(Map<String, Object> map) {
		insert("board.setCommentReplyInsert", map);
	}
	
	//코멘트 orderIndex을 업데이트 하는 부분
	public void setCommentReplyUpdateIndex(Map<String, Object> map) {
		insert("board.setCommentReplyUpdateIndex", map);
	}
	
	//코멘트 답글을 중간에 삽입하는 부분
	public void setCommentReplyInsertOfIndex(Map<String, Object> map) {
		insert("board.setCommentReplyInsertOfIndex", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getCommentView(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("board.getCommentView", map);
	}
	
	//코멘트 글을 업데이트 함.
	public void setCommentUpdate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		update("board.setCommentUpdate", map);
	}
	
	/*//파일을 디비에 넣는 곳
	public void setFileInsert(Map<String, Object> map) {
		insert("common.setFileInsert", map);
	}*/

	/*//파일목록을 가져오는 부분
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFileList(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectList("board.getFileList", map);
	}*/
	
	//
	public Object setCommentDelete(Map<String, Object> map) {
		return delete("board.setCommentDelete", map);
	}
	
	//orderIndex에 의한 삭제
	public Object setCommentDeleteOfOrderIndex(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return delete("board.setCommentDeleteOfOrderIndex", map);
	}
	
	//게시글을 삭제함.
	public void setBoardDeleteOfOrderIndex(Map<String, Object> map) {
		delete("board.setBoardDeleteOfOrderIndex", map);
	}
	
	//게시글에 달린 전체 코멘트 삭제.
	public void setCommentAll(Map<String, Object> map) {
		delete("board.setCommentAll",map);
	}
	
	/*//게시글과 관련된 파일삭제
	public void setFileDelete(Map<String, Object> map) {
		delete("board.setFileDelete", map);
	}*/

	/*@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFileNames(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("board.getFileNames", map);
	}*/
	
	/*//게시글과 관련된 파일삭제
	public void setOneFileDelete(Map<String, Object> map) {
		delete("board.setOneFileDelete", map);
	}*/
	
	//수정페이지에서 파일이름과 관련된 요소를 지우기 위해 파일 이름을 가져옴.
	/*@SuppressWarnings("unchecked")
	public Map<String, Object> getFileName(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("board.getFileName", map);
	}*/
	
	//파일업로드한 내용을 업데이트 함.
	/*public void setFileUpdate(Map<String, Object> map) {
		update("common.setFileUpdate", map);
		
	}*/
	
	//현재페이지를 처음으로 보았는지 판단.
	public void setBoardViewInsert(Map<String, Object> map) {
		insert("board.setBoardViewInsert",map);
	}
	
	//현재페이지를 처음으로 보았을때 데이터 입력
	@SuppressWarnings("unchecked")
	public Map<String, Object> getBoardViewCount(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("board.getBoardViewCount", map);
	}
	
	//OrderIndex의 최대값을 가져옴.
	public int getBoardMaxOrderIndex(Map<String, Object> map) {
		return (Integer) selectOne("board.getBoardMaxOrderIndex", map);
	}

	public void setRecommandation(Map<String, Object> map) {
		update("board.setRecommandation", map);
	}

	public void setNoRecommandation(Map<String, Object> map) {
		update("board.setNoRecommandation", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getRecommandationCount(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("board.getRecommandationCount", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getNoRecommandationCount(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("board.getNoRecommandationCount", map);
	}
	
	//보드의 추천수를 업데이트
	public void setBoardRecomandationUpdate(Map<String, Object> map) {
		selectOne("board.setBoardRecommandationUpdate", map);
	}

	//보드의 비추천수를 업데이트
	public void setBoardNoRecomandationUpdate(Map<String, Object> map) {
		selectOne("board.setBoardNoRecommandationUpdate", map);
	}

	public int getBoardRecommandationUpdate(Map<String, Object> map) {
		return (Integer) selectOne("board.getBoardRecommandationUpdate", map);
	}

	public int getBoardNoRecommandationUpdate(Map<String, Object> map) {
		return (Integer) selectOne("board.getBoardNoRecommandationUpdate", map);
	}

	//
	/*@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTempFileList(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("board.getTempFileList", map);
	}*/
	
}
