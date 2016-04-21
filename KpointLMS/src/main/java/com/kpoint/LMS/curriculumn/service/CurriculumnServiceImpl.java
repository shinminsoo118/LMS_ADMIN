package com.kpoint.LMS.curriculumn.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kpoint.LMS.common.dao.CommonDAO;
import com.kpoint.LMS.common.util.FileUtils;
import com.kpoint.LMS.curriculumn.dao.CurriculumnDAO;

@Service("curriculumnService")
public class CurriculumnServiceImpl implements CurriculumnService{
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="curriculumnDAO")
	private CurriculumnDAO curriculumnDAO;
	
	@Resource(name="commonDAO")
	private CommonDAO commonDAO;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	

	@Override
	public Map<String, Object> getCurriculumnDetail(Map<String, Object> map) {
		return curriculumnDAO.getCurriculmnDetail(map);
	}

	@Override
	public Map<String, Object> getCurriculumnRegister(Map<String, Object> map) {
		return curriculumnDAO.getCurriculmnRegist(map);
	}

	@Override
	public List<Map<String, Object>> getProductCodeList(Map<String, Object> map) {
		return curriculumnDAO.getProductCodeList(map);
	}
	
	//교육과정 상세정보를 가져오는 부분.
	@Override
	public Map<String, Object> getProductDetailInfo(Map<String, Object> map) {
		return curriculumnDAO.getProductDetailInfo(map);
	}
	
	//교육과정 내용을 삽입하는 부분
	@Override
	public void setCurriculumnInsert(Map<String, Object> map){
		curriculumnDAO.setCurriculumnInsert(map);
		int returnSEQ= Integer.parseInt(map.get("SEQ").toString());
		map.put("SEQ", returnSEQ);
		if(map.get("TempFILEWRITER") != null){
			curriculumnDAO.setFileUpdate(map);
		}
	}

	@Override
	public Map<String, Object> getCurriculumnList(Map<String, Object> map) {
		int countMap = curriculumnDAO.getTotalList(map);
		map.put("totalListCount",countMap);
		return curriculumnDAO.getCurriculumnList(map);
	}

	@Override
	public void setCurriculumnUpdate(Map<String, Object> map) {
		curriculumnDAO.setCurriculumnUpdate(map);
		if(map.get("TempFILEWRITER") != null){
			curriculumnDAO.setFileUpdate(map);
		}
	}
	
	@Override
	public void setCurriculumnDelete(Map<String, Object> map, HttpServletRequest request){
		
		curriculumnDAO.setCurriculumnDelete(map);
		//서버에 있는 파일 삭제
		List<Map<String, Object>> fileNames = commonDAO.getFileLists(map);
		
		String boardPathMatch = (String)fileNames.get(0).get("BOARDID");

		String boardPath = fileUtils.setSubFilePath(boardPathMatch);
		
		fileUtils.setFileDeletes(fileNames, request, boardPath);
		//DB에서 파일이름 삭제
		commonDAO.setFileDelete(map);
		
	}
	
	@Override
	public String setCourseRegInsert(Map<String, Object> map){
		
		String resStatus = "";
		
		if(map.get("CURPAY") != null){
			if(map.get("CURPAY").toString() != null && map.get("CURPAY").toString() != ""
					&& map.get("CURPAY").toString().length() > 0){
				map.put("STATUS", "P");
			}
		}else{
			map.put("STATUS", "S");
			map.put("CURPAY", "결제대기");
		}
		
		//수강신청 초과하는지 체크 
		int curMemberCount = curriculumnDAO.getCurMemberCount(map);
		
		if(Integer.parseInt(map.get("CURMEMBERCOUNT").toString()) <= curMemberCount){
			resStatus = "fail";
		}else{
			curriculumnDAO.setCourseRegInsert(map);
			resStatus = "success";
		}
		return resStatus;
	}
	
}
