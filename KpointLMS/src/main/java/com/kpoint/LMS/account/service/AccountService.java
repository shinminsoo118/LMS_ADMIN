package com.kpoint.LMS.account.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService{
	//ȸ�������� ���
	public Object memberInsert(Map<String, Object> map);
	
	public void memberLoginInfo(Map<String, Object> map);
}
