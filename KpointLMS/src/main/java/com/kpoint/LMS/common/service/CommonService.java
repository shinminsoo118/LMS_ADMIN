package com.kpoint.LMS.common.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface CommonService {
	
	/************************************************************************/	
	/*			          		�Խ��� �������� �κ�							*/
	/************************************************************************/
	
	Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
	Map<String, Object> setFileInsert(Map<String, Object> map, HttpServletRequest request) throws Exception;

	void setFiles(Map<String, Object> map, HttpServletRequest request) throws Exception;

	void setFileDelete(Map<String, Object> map, HttpServletRequest request);

	void boardConfigSetting(Map<String, Object> map);
	
	public Map<String, Object> boardConfigGetting();
	
	List<Map<String, Object>> getFileList(Map<String, Object> map);

	void setFileListDelete(Map<String, Object> map, HttpServletRequest request);
}
