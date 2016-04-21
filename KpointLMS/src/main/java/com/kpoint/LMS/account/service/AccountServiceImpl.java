package com.kpoint.LMS.account.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kpoint.LMS.account.dao.AccountDAO;
import com.kpoint.LMS.account.vo.UserDetailsVO;

@Service("accountService")
public class AccountServiceImpl implements AccountService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="accountDAO")
	private AccountDAO accountDAO;
	
	@SuppressWarnings("unused")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username_service = " + username);
		Map<String, Object> users = accountDAO.getMember(username);
		if(users == null ) throw new UsernameNotFoundException(username);
		List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		
	    gas.add(new SimpleGrantedAuthority(users.get("USERROLE").toString()));
	    
	    System.out.println(users.get("username").toString() + "-" + users.get("password").toString() +" - " + users.get("USEREMAIL").toString());
	    return new UserDetailsVO(users.get("username").toString(), 
	    						 users.get("password").toString(), 
	    						 true, 
	    						 true, true, true, gas, 
	    						 users.get("USEREMAIL").toString(), 
	    						 users.get("USERNAME").toString(), 
	    						 users.get("USERIMG").toString());
	    
	}
	
	//ȸ�������� �Է��ϴ� �κ�
	@Override
	public Object memberInsert(Map<String, Object> map) {
		//���� ȸ�����̵� �ִ��� ��ȸ
		Object isUser = accountDAO.getIsMember(map);
		System.out.println("User�� �ִ���? = "+isUser);
		int count = 0;
		
		if(isUser == null){
			//���� ȸ�����̵� ��������� ���ٸ� ȸ������ ���
			count = accountDAO.setMemberInsert(map);
			accountDAO.setMemberRoleInsert(map);
		}else{
			//���� ȸ���� �ִٸ� null���� ����
			return null;
		}
		
		return count;
	}
	
	@Override
	public void memberLoginInfo(Map<String, Object> map){
		accountDAO.setMemberLoginInfo(map);
	}
}
