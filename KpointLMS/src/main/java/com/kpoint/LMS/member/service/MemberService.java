package com.kpoint.LMS.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MemberService{
	public Map<String, Object> memberList(Map<String, Object> map);

	public void memberDelete(Map<String, Object> map);

	public Map<String, Object> memberDetail(Map<String, Object> map);

	public void memberUpdate(Map<String, Object> map, HttpServletRequest request) throws Exception;

	public void memberRoleUpdate(Map<String, Object> map);
	
	public String setMailSend(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> getUserMailList(Map<String, Object> map);
	
	public List<Map<String, Object>> getUserSmsList(Map<String, Object> map);
	
	public List<Map<String, Object>> getUserGroupList(Map<String, Object> map);
	
	public Map<String, Object> getMailList(Map<String, Object> map);
	
	public Map<String, Object> getMailDetail(Map<String, Object> map);
	
	public Map<String, Object> getSMSList(Map<String, Object> map);
	
	public String setSmsSend(Map<String, Object> map);
	
	public Map<String,Object> getSmsDetail(Map<String, Object> map);
}
