package com.kpoint.LMS.lecture.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface LectureService {
	
	/************************************************************************/	
	/*							Lecture									*/
	/************************************************************************/
	
	public Map<String, Object> getSignUpList(Map<String, Object> map);
	
	public Map<String, Object> getSignDetail(Map<String, Object> map);
	
	public int setLecturePaymentUpdate(Map<String, Object> map);
	
	public int setLectureSignUpDelete(Map<String, Object> map); 
	
	public int setLectureRefindUpdate(Map<String, Object> map);
	
}
