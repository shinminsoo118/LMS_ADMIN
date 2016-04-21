package com.kpoint.LMS.setup.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.kpoint.LMS.common.dao.AbstractDAO;

@Repository("setupDAO")
public class SetupDAO extends AbstractDAO{

	public Integer getTotalListCount(Map<String, Object> map) {
		return (Integer) selectOne("setup.getTotalListCount", map);
	}
	
	public Integer getMemberEmailCheck(Map<String, Object> map) {
		return (Integer) selectOne("setup.getMemberEmailCheck", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getProductList(Map<String, Object> map) {
		return (Map<String, Object>)selectPagingList("setup.getProductList", map);
	}

	public void setProductCodeInsert(Map<String, Object> map) {
		insert("setup.setProductCodeInsert",map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getProductCode(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("setup.getProductCode", map);
	}

	public void setProductCodeUpdate(Map<String, Object> map) {
		update("setup.setProductCodeUpdate", map);
	}
	
	public void setCodeDelete(Map<String, Object> map){
		delete("setup.setCodeDelete", map);
	}

	public int getBoardTotalList(Map<String, Object> map){
		return (Integer) selectOne("setup.getBoardTotalList",map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getBoardList(Map<String, Object> map) {
		return (Map<String, Object>)selectPagingList("setup.getBoardList", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getBoardSetupDetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Map<String, Object>) selectOne("setup.getBoardSetupDetail",map);
	}
	
	public void setBoardSetupRoleUpdate(Map<String, Object> map) {
		update("setup.setBoardSetupRoleUpdate", map);
	}
	
	public void setBoardSetupDetailUpdate(Map<String, Object> map) {
		update("setup.setBoardSetupDetailUpdate", map);
	}
	
	public int setBoardSetupInsert(Map<String, Object> map) {
		return (Integer)update("setup.setBoardSetupInsert", map);
	}
	
	public Object getBoardConfig(Map<String, Object> map) {
		return selectOne("setup.getBoardConfig", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTableList() {
		return selectList("setup.getTableList");
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getUserRoleList() {
		System.out.println("User Role");
		return selectList("setup.getUserRoleList");
	}
	
	public Integer adminListTotalCount(Map<String, Object> map){
		return (Integer) selectOne("setup.getAdminTotalList", map);
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> adminList(Map<String, Object> map) {
		return (Map<String, Object>)selectPagingList("setup.getAdminList", map);
	}
	
	public int setSetupAdminInsert(Map<String, Object> map) {
		Object memberKey = insert("setup.setSetupAdminInsert",map);
		return (Integer) memberKey;
	}
	
	public int setMemberRoleInsert(Map<String, Object> map) {
		Object memberKey = insert("setup.setSetupAdminRoleInsert", map);
		return (Integer) memberKey;
	}
	
}
