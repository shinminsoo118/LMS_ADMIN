package com.kpoint.LMS.setup.service;

import java.util.Map;

public interface SetupService {
	
	//상품코드 리스트를 가져오는 부분
	public Map<String, Object> getProductList(Map<String, Object> map);
	
	//상품코드를 등록하는 부분
	public int setProductCodeInsert(Map<String, Object> map);
	
	//상품코드 상세정보를 가져오는 부분
	public Map<String, Object> getProductCode(Map<String, Object> map);
	
	//상품코드 상세정보를 가져오는 부분
	public Map<String, Object> setProductCodeUpdate(Map<String, Object> map);

	public Map<String, Object> getBoardList(Map<String, Object> map);

	public Map<String, Object> getBoardSetupDetail(Map<String, Object> map);
	
	public Map<String, Object> getAdminList(Map<String, Object> map);
	
	public Object setSetupAdminInsert(Map<String, Object> map);
	
	public void setCodeDelete(Map<String, Object> map);
	
	public void setBoardSetupRoleUpdate(Map<String, Object>	map);
	
	public void setBoardSetupDetailUpdate(Map<String, Object> map);
	
	public Object setBoardSetupInsert(Map<String, Object> map);
	
	public Object getMemberEmailCheck(Map<String, Object> map);
	
}
