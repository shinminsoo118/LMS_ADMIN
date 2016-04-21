package com.kpoint.LMS.document.service;

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
import com.kpoint.LMS.document.dao.DocumentDAO;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService{
	
	Logger log = Logger.getLogger(this.getClass());
	 
	@Resource(name="documentDAO")
	private DocumentDAO documentDAO;
	
	@Resource(name="commonDAO")
	private CommonDAO commonDAO;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	//Document List을 가져오는 부분
	@Override
	public Map<String, Object> getDocumentList(Map<String, Object> map) {
		int countMap = documentDAO.getTotalList(map);
		map.put("totalListCount",countMap);
		return documentDAO.getDocumentList(map);
	}
	
	@Override
	public void setDocumentInsert(Map<String, Object> map, HttpServletRequest request)throws Exception{
		documentDAO.setDocumentInsert(map);
		log.debug("doc TempFILEWRITER ====" + map.get("TempFILEWRITER"));;
		if(map.get("TempFILEWRITER") != null){
			log.debug("doc insert ====" + map.get("SEQ"));;
			commonDAO.setFileUpdate(map);
		}
		//fileupload 나중에 하기
	}
	
	@Override
	public Map<String, Object> getDocumentDetail(Map<String, Object> map){
		
		Map<String, Object> viewMap = documentDAO.getDocumentViewCount(map);
		int viewCount = Integer.parseInt(viewMap.get("SEQCOUNT").toString());
		
		if(viewCount == 0){
			documentDAO.setDocumentViewInsert(map);
			documentDAO.setDocumentHitUpdate(map);
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> detail = documentDAO.getDocumentDetail(map);
		List<Map<String, Object>> fileList = commonDAO.selectFileList(map);
		
		returnMap.put("detail", detail);
		returnMap.put("fileList", fileList);
		
		return returnMap;
	}
	
	//Board 글을 가져오는 부분
	@Override
	public Map<String, Object> getDocumentUpdate(Map<String, Object> map){
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> article = documentDAO.getDocumentUpdate(map);
		List<Map<String, Object>> fileList = commonDAO.selectFileList(map);
		
		returnMap.put("article", article);
		returnMap.put("fileList", fileList);
		
		return returnMap;
	}
	
	//Board 긍르 수정하는 부분
	@Override
	public void setDocumentUpdate(Map<String, Object> map, HttpServletRequest request)throws Exception{
	
		documentDAO.setDocumentUpdate(map);
		if(map.get("TempFILEWRITER") != null){
			commonDAO.setFileUpdate(map);
		}
	}
	
	//Board 항목을 삭제하는 부분
	@Override
	public void setDocumentDelete(Map<String, Object> map, HttpServletRequest request){
		Object orderIndex = documentDAO.getDocumentOrderIndex(map);
		if(orderIndex == null){
			documentDAO.setDocumentDelete(map);
		}else{
			map.put("orderIndex", orderIndex);
			documentDAO.setDocumentDeleteOfOrderIndex(map);
		}
		
		//코멘트 삭제
		documentDAO.setDocCommentAll(map);
		
		//서버에 있는 파일 삭제
		List<Map<String, Object>> fileNames = commonDAO.getFileLists(map);
		
		String boardPathMatch = (String)fileNames.get(0).get("BOARDID");

		String boardPath = fileUtils.setSubFilePath(boardPathMatch);
		
		fileUtils.setFileDeletes(fileNames, request, boardPath);
		//DB에서 파일이름 삭제
		commonDAO.setFileDelete(map);
	}
	
}
