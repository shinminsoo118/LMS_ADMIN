package com.kpoint.LMS.curriculumn.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CurriculumnService {

	Map<String, Object> getCurriculumnDetail(java.util.Map<String, Object> map);

	Map<String, Object> getCurriculumnRegister(Map<String, Object> map);

	List<Map<String, Object>> getProductCodeList(Map<String, Object> map);

	Map<String, Object> getProductDetailInfo(Map<String, Object> map);

	void setCurriculumnInsert(Map<String, Object> map);

	Map<String, Object> getCurriculumnList(Map<String, Object> map);

	void setCurriculumnUpdate(Map<String, Object> map);
	
	void setCurriculumnDelete(Map<String, Object> map, HttpServletRequest request);
	
	String setCourseRegInsert(Map<String, Object> map);

}
