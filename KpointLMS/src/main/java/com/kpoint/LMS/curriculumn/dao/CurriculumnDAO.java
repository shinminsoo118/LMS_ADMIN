package com.kpoint.LMS.curriculumn.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.kpoint.LMS.common.dao.AbstractDAO;

@Repository("curriculumnDAO")
public class CurriculumnDAO extends AbstractDAO{
	Logger log = Logger.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public Map<String, Object> getCurriculmnDetail(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("curriculumn.getCurriculmnDetail", map);
	}

	public Map<String, Object> getCurriculmnRegist(Map<String, Object> map) {
		//return (Map<String, Object>) selectList("curriculumn.getCurriculumnRegist", map);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getProductCodeList(Map<String, Object> map) {
		return selectList("curriculumn.getProductCodeList", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getProductDetailInfo(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("curriculumn.getProductDetailInfo", map);
	}

	public void setCurriculumnInsert(Map<String, Object> map) {
		insert("curriculumn.setCurriculumnInsert", map);
	} //setCourseRegInsert
	
	public void setCourseRegInsert(Map<String, Object> map) {
		insert("curriculumn.setCourseRegInsert", map);
	}
	
	public int getTotalList(Map<String, Object> map) {
		return (Integer) selectOne("curriculumn.getTotalList", map);
	}

	public int getCurMemberCount(Map<String, Object> map) {
		return (Integer) selectOne("curriculumn.getCurMemberCount", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCurriculumnList(Map<String, Object> map) {
		return selectPagingList("curriculumn.getCurriculumnList", map);
	}

	public void setCurriculumnUpdate(Map<String, Object> map) {
		update("curriculumn.setCurriculumnUpdate", map);
	}

	public void setFileUpdate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		update("common.setFileUpdate", map);
	}
	
	public void setCurriculumnDelete(Map<String, Object> map){
		update("curriculumn.setCurriculumnDelete", map);
	}

	
}
