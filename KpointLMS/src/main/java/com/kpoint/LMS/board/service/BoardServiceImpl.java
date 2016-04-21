package com.kpoint.LMS.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kpoint.LMS.board.dao.BoardDAO;
import com.kpoint.LMS.common.dao.CommonDAO;
import com.kpoint.LMS.common.util.FileUtils;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	Logger log = Logger.getLogger(this.getClass());
	 
	@Resource(name="boardDAO")
	private BoardDAO boardDAO;
	
	@Resource(name="commonDAO")
	private CommonDAO commonDAO;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	private Map<String, Object> boardConfig = null;
	
	//Board List을 가져오는 부분
	@Override
	public Map<String, Object> getBoardList(Map<String, Object> map) {
		int countMap = boardDAO.getTotalList(map);
		map.put("totalListCount",countMap);
		return boardDAO.getBoardList(map);
	}
	
	//Board 상세정보를 가져오는 부분
	public Map<String, Object> getBoardDetail(Map<String, Object> map){
		//오늘날짜보다 만하루 작은은지 체크
		Map<String, Object> viewMap = boardDAO.getBoardViewCount(map);
		int viewCount = Integer.parseInt(viewMap.get("SEQCOUNT").toString());
		
		//만하루보다 이후이면 viewCount + 1, TBL_VIEW_UPDATES 테이블에 값 입력.
		if(viewCount == 0){
			boardDAO.setBoardViewInsert(map);
			boardDAO.setBoardHitUpdate(map);
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> detail = boardDAO.getBoardDetail(map);
		List<Map<String, Object>> fileList = commonDAO.selectFileList(map); //파일리스트를 가져옴.
		returnMap.put("detail", detail);
		returnMap.put("fileList", fileList);
		System.out.println("fileList = " + fileList);
		return returnMap;
	}
	
	//추천수를 늘리는 부분
	@Override
	public int setRecommandation(Map<String, Object> map) {
		Map<String, Object> viewMap = boardDAO.getRecommandationCount(map);
		int viewCount = Integer.parseInt(viewMap.get("SEQCOUNT").toString());
		
		//만하루보다 이후이면 추천수를 하나 증가시킴.
		if(viewCount == 0){
			boardDAO.setRecommandation(map);
			boardDAO.setBoardRecomandationUpdate(map);
			return boardDAO.getBoardRecommandationUpdate(map);
		}else{
//			System.out.println("RECOMMANDATION :::::::::" + map.get("RECOMMANDATION"));
//			System.out.println("RECOMMANDATION :::::::::" + map.get("RECOMMANDATION").getClass().getCanonicalName());
			
//			String RECOMMANDATION = (String) map.get("RECOMMANDATION");
			return boardDAO.getBoardRecommandationUpdate(map);
			//return (Integer) map.get("RECOMMANDATION");
		}
	}

	//비추천수를 늘리는 부분
	@Override
	public int setNoRecommandation(Map<String, Object> map) {
		Map<String, Object> viewMap = boardDAO.getNoRecommandationCount(map);
		int viewCount = Integer.parseInt(viewMap.get("SEQCOUNT").toString());
		
		//만하루보다 이후이면 추천수를 하나 증가시킴.
		if(viewCount == 0){
			boardDAO.setNoRecommandation(map);
			boardDAO.setBoardNoRecomandationUpdate(map);
			return boardDAO.getBoardNoRecommandationUpdate(map);
		}else{
			//만하루가 아닐경우 파라미터 비추천수를 돌려보냄.
//			System.out.println("NORECOMMANDATION :::::::::" + map.get("NORECOMMANDATION"));
//			System.out.println("NORECOMMANDATION :::::::::" + map.get("NORECOMMANDATION").getClass().getCanonicalName());
			
			return boardDAO.getBoardNoRecommandationUpdate(map);
			//return (Integer) map.get("NORECOMMANDATION");
		}
		
		
	}
	
	//Board 새글을 입력하는 부분
	@Override
	public void setBoardInsert(Map<String, Object> map, HttpServletRequest request) throws Exception {
		boardDAO.setBoardInsert(map);
		if(map.get("TempFILEWRITER") != null){
			commonDAO.setFileUpdate(map);
		}
	}
	
	//Board 새로운 답글을 입력하는 부분
	@Override
	public void setBoardReplyInsert(Map<String, Object> map){
		int DEPTH =  Integer.parseInt(map.get("DEPTH").toString());
		map.put("DEPTH", DEPTH);
		Object orderIndex = boardDAO.getBoardOrderIndex(map);
		if(orderIndex == null){
			System.out.println("GROUPID : " + map.get("GROUPID"));
			int maxOrderIndex = boardDAO.getBoardMaxOrderIndex(map);
			map.put("MAXORDERINDEX", maxOrderIndex);
			boardDAO.setBoardReplyInsert(map);
		}else{
			map.put("ORDERINDEX", orderIndex);
			boardDAO.setBoardReplyUpdateIndex(map);
			boardDAO.setBoardReplyInsertIndex(map);
		}
		
		System.out.println("INSERTSEQ = " + map.get("INSERTSEQ"));
		
		if(map.get("TempFILEWRITER") != null){
			map.put("SEQ", map.get("INSERTSEQ"));
			commonDAO.setFileUpdate(map);
		}
	}
	
	//Board 글을 가져오는 부분.
	@Override
	public Map<String, Object> getBoardUpdate(Map<String, Object> map) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> article = boardDAO.getBoardUpdate(map); 
		List<Map<String, Object>> fileList = commonDAO.selectFileList(map);
		returnMap.put("article", article);
		returnMap.put("fileList", fileList);
		return returnMap;
	}
	
	//Board 글을 수정하는 부분
	@Override
	public void setBoardUpdate(Map<String, Object> map, HttpServletRequest request) throws Exception {
		boardDAO.setBoardUpdate(map);
		//임시Writer를 실작성자로 업데이트 
		if(map.get("TempFILEWRITER") != null){
			commonDAO.setFileUpdate(map);
		}
	}
	
	//Board 항목을 삭제하는 부분
	@Override
	public void setBoardDelete(Map<String, Object> map, HttpServletRequest request) {
		
		Object orderIndex = boardDAO.getBoardOrderIndex(map);

		if(orderIndex == null){
			boardDAO.setBoardDelete(map);
		}else{
			map.put("orderIndex", orderIndex);
			boardDAO.setBoardDeleteOfOrderIndex(map);
		}
		
		//코멘트 삭제.
		boardDAO.setCommentAll(map);
		
		//서버에 있는 파일삭제.
		List<Map<String, Object>> fileNames = commonDAO.getFileNames(map);
		
		String boardPathMatch = (String)fileNames.get(0).get("BOARDID");
		
		String boardPath = fileUtils.setSubFilePath(boardPathMatch);
		
		fileUtils.setFileDelete(fileNames, request, boardPath);
		
		//DB에서 파일이름 삭제.
		commonDAO.setFileDelete(map);
		
	}
	
	//코멘트 리스트를 가져오는 부분
	@Override
	public List<Map<String, Object>> getCommentList(Map<String, Object> map) {
		return (List<Map<String, Object>>) boardDAO.getCommentList(map);
	}
	
	//새로운 코멘트를 입력하는 부분
	@Override
	public void setCommentInsert(Map<String, Object> map) {
		map.put("COUNT", 1);
		boardDAO.setBoardCommentCountUpdate(map);
		boardDAO.setCommentInsert(map);
	}
	
	//코멘트 새로운 글을 등록하는 부분
	@Override
	public void setCommentReplyInsert(Map<String, Object> map) {
		
		int DEPTH =  Integer.parseInt(map.get("DEPTH").toString()); 
		map.put("DEPTH", DEPTH);
		Object orderIndex = boardDAO.getCommentOrderIndex(map);
		System.out.println("*** ORDER INDEX = "+orderIndex);
		map.put("COUNT", 1);
		boardDAO.setBoardCommentCountUpdate(map);
		if(orderIndex == null){
			boardDAO.setCommentReplyInsert(map);
		}else{
			map.put("ORDERINDEX", orderIndex);
			boardDAO.setCommentReplyUpdateIndex(map);
			boardDAO.setCommentReplyInsertOfIndex(map);
		}
	}
	
	//코멘트를 수정하는 부분
	@Override
	public Map<String, Object> setCommentUpdate(Map<String, Object> map) {
		boardDAO.setCommentUpdate(map);
		return boardDAO.getCommentView(map);
	}
	
	//코멘트를 삭제하는 부분
	@Override
	public void setCommentDelete(Map<String, Object> map) {

		Object orderIndex = boardDAO.getCommentOrderIndex(map);
		int deleteCount = 0; 
		if(orderIndex == null){
			deleteCount = (Integer) boardDAO.setCommentDelete(map);
		}else{
			map.put("orderIndex", orderIndex);
			deleteCount = (Integer) boardDAO.setCommentDeleteOfOrderIndex(map);
		}
		map.put("COUNT",-deleteCount);
		boardDAO.setBoardCommentCountUpdate(map);
		
	}
	
	//코멘트 수정을 위해 글을 가져오는 부분.
	@Override
	public Map<String, Object> getCommentView(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardDAO.getCommentView(map);
	}
	
	//첨부파일을 삭제하는 부분.
	@Override
	public void setFileDelete(Map<String, Object> map, HttpServletRequest request) {
		
		
		Map<String, Object> fileNames = commonDAO.getFileName(map);
		String fileName = (String) fileNames.get("FILESTRNAME");
		
		String boardPathMatch = (String)fileNames.get("BOARDID");
		
		String boardPath = fileUtils.setSubFilePath(boardPathMatch);
		
		fileUtils.setOneFileDelete(fileName, request, boardPath); //서버에 저장되어 있는 파일내용 삭제
		commonDAO.setOneFileDelete(map); //디비에 저장되어 있는 파일관련 내용 삭제
	}

	
	
	/*//파일을 저장하는 부분
	@Override
	public void setFiles(Map<String, Object> map, HttpServletRequest request) throws Exception {
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0, size=list.size(); i<size ; i++){
			commonDAO.setFileInsert(list.get(i));
			//throw new RuntimeException();
		}
	}*/
}
