package com.kpoint.LMS.setup.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kpoint.LMS.account.dao.AccountDAO;
import com.kpoint.LMS.setup.dao.SetupDAO;

@Service("setupService")
public class SetupServiceImpl implements SetupService{
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="setupDAO")
	private SetupDAO setupDAO;

	@Resource(name="accountDAO")
	private AccountDAO accountDAO;
	
	//상품코드 목록을 가져오는 부분
	@Override
	public Map<String, Object> getProductList(Map<String, Object> map) {
		int count = setupDAO.getTotalListCount(map);
		map.put("totalListCount", count);
		return setupDAO.getProductList(map);
	}
	
	//상품코드를 등록하는 부분.
	@Override
	public int setProductCodeInsert(Map<String, Object> map) {
		int retValue = 0;
		try{
			setupDAO.setProductCodeInsert(map);
			retValue = 1;
		}catch(Exception e){
			log.error("sql exception :" + e.getMessage());
		}
		
		return retValue;
	}
	
	//상품코드 상세정보를 가져오는 부분.
	@Override
	public Map<String, Object> getProductCode(Map<String, Object> map) {
		setupDAO.getProductCode(map);
		return setupDAO.getProductCode(map);
	}
	
	//상품코드 상세정보를 가져오는 부분.
	@Override
	public Map<String, Object> setProductCodeUpdate(Map<String, Object> map) {
		setupDAO.setProductCodeUpdate(map);
		return setupDAO.getProductCode(map);
	}
	
	@Override
	public void setCodeDelete(Map<String, Object> map) {
		setupDAO.setCodeDelete(map);
//		return setupDAO.getProductCode(map);
	}

	@Override
	public Map<String, Object> getBoardList(Map<String, Object> map) {
		int countMap = setupDAO.getBoardTotalList(map);
		map.put("totalListCount",countMap);
		Map<String, Object> resultMap = setupDAO.getBoardList(map);
		List<Map<String, Object>> userRole = setupDAO.getUserRoleList();
		resultMap.put("userRole", userRole);
		return resultMap;
	}

	@Override
	public Map<String, Object> getBoardSetupDetail(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("detail", setupDAO.getBoardSetupDetail(map));
		resultMap.put("tableList", setupDAO.getTableList());
		List<Map<String, Object>> userRole = setupDAO.getUserRoleList();
		resultMap.put("userRole", userRole);
		return resultMap;
	}
	
	@Override
	public void setBoardSetupRoleUpdate(Map<String, Object> map){
		setupDAO.setBoardSetupRoleUpdate(map);
	}
	
	@Override
	public Object getMemberEmailCheck(Map<String, Object> map){
		Object emailVal = setupDAO.getMemberEmailCheck(map);
		
		return emailVal;
	}
	
	@Override
	public Map<String, Object> getAdminList(Map<String, Object> map){
		int totalCount = setupDAO.adminListTotalCount(map);
		map.put("totalListCount", totalCount);
		return (Map<String,Object>) setupDAO.adminList(map);
	}
	
	@Override
	public Object setSetupAdminInsert(Map<String, Object> map){
		
		Object isUser = accountDAO.getIsMember(map);
		
		int count = 0;
		
		if(isUser == null){
			count = setupDAO.setSetupAdminInsert(map);
			setupDAO.setMemberRoleInsert(map);
		}else{
			return null;
		}
		return count;
	}
	
	@Override
	public void setBoardSetupDetailUpdate(Map<String, Object> map){
		setupDAO.setBoardSetupDetailUpdate(map);
	}
	
	@Override
	public Object setBoardSetupInsert(Map<String, Object> map){
		
		Object isBoardConfig = setupDAO.getBoardConfig(map);
		
		int count = 0;
		
		if(isBoardConfig == null){
			count = setupDAO.setBoardSetupInsert(map);
		}else{
			return null;
		}
		
		return count;
	}
	
}
