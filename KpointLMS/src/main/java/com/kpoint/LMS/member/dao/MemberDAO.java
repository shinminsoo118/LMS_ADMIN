package com.kpoint.LMS.member.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.kpoint.LMS.common.dao.AbstractDAO;

@Repository("memberDAO")
public class MemberDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public Map<String, Object> memberList(Map<String, Object> map) {
		return (Map<String, Object>)selectPagingList("member.memberList", map);
	}
	
	public Integer memberTotalCount(Map<String, Object> map){
		return (Integer) selectOne("member.getTotalList", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> mailList(Map<String, Object> map) {
		return (Map<String, Object>)selectPagingList("member.getMailList", map);
	}
	
	public Integer mailTotalCount(Map<String, Object> map){
		return (Integer) selectOne("member.getMailTotalList", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSMSList(Map<String, Object> map) {
		return (Map<String, Object>)selectPagingList("member.getSmsList", map);
	}
	
	public Integer smsTotalCount(Map<String, Object> map){
		return (Integer) selectOne("member.getSmsTotalList", map);
	}

	public void memberDelete(Map<String, Object> map) {
		delete("member.memberDelete", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> memberDetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Map<String,Object>) selectOne("member.memberDetail", map);
	}

	public void memberUpdate(Map<String, Object> map) {
		update("member.memberUpdate", map);
	}

	public void memberProfileImgUpload(Map<String, Object> map) {
		insert("member.memberProfileImgUpload", map);
	}

	public void memberRoleUpdate(Map<String, Object> map) {
		update("member.memberRoleUpdate", map);
	}
	
	public void setMailInsert(Map<String, Object> map ){
		int boardId = (Integer) insert("member.setMailInsert", map); 
	}
	
	public int setSmsInsert(Map<String, Object> map ){
		int smsCount = (Integer) insert("member.setSmsInsert", map); 
		return smsCount;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> mailDetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Map<String,Object>) selectOne("member.getMailDetail", map);
	} 
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSmsDetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Map<String,Object>) selectOne("member.getSmsDetail", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> userMailList(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("member.userMailList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getUserSmsList(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("member.userSmsList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> userGroupList(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("member.userGroupList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> groupSearch(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("member.groupSearchList", map);
	}
	
	
}

