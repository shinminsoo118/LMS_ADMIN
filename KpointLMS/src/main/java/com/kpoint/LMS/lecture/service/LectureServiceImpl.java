package com.kpoint.LMS.lecture.service;

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
import com.kpoint.LMS.lecture.dao.LectureDAO;

@Service("lectureService")
public class LectureServiceImpl implements LectureService{
	
	Logger log = Logger.getLogger(this.getClass());
	 
	@Resource(name="lectureDAO")
	private LectureDAO lectureDAO;
	
//	@Resource(name="commonDAO")
//	private CommonDAO commonDAO;
//	
//	@Resource(name="fileUtils")
//	private FileUtils fileUtils;
	
	
	@Override
	public Map<String, Object> getSignUpList(Map<String, Object> map){
		int countMap = lectureDAO.getTotalList(map);
		map.put("totalListCount",countMap);
		return lectureDAO.getSignUpList(map);		
	}
	
	@Override
	public Map<String, Object> getSignDetail(Map<String, Object> map){
		return lectureDAO.getSignDetail(map);
	}
	
	@Override
	public int setLecturePaymentUpdate(Map<String, Object> map){
		int retValue = 0;
		try{
			lectureDAO.setLecturePaymentUpdate(map);
			retValue = 1;
		}catch(Exception e){
			log.error("sql exception :" + e.getMessage());
		}
		
		return retValue;
	}  //
	
	@Override
	public int setLectureRefindUpdate(Map<String, Object> map){
		int retValue = 0;
		try{
			lectureDAO.setLectureRefindUpdate(map);
			retValue = 1;
		}catch(Exception e){
			log.error("sql exception :" + e.getMessage());
		}
		
		return retValue;
	}
	
	@Override
	public int setLectureSignUpDelete(Map<String, Object> map){
		int retValue = 0;
		try{
			lectureDAO.setLectureSignUpDelete(map);
			retValue = 1;
		}catch(Exception e){
			log.error("sql exception :" + e.getMessage());
		}
		
		return retValue;
	}
	
}
