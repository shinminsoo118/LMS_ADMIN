package com.kpoint.LMS.account.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kpoint.LMS.common.dao.AbstractDAO;

@Repository("accountDAO")
public class AccountDAO extends AbstractDAO{
	//ȸ���α��� ���úκ�
		@SuppressWarnings("unchecked")
		public Map<String, Object> getMember(String username) {
			// TODO Auto-generated method stub
			return (Map<String, Object>) selectOne("account.getMember", username);
		}
		
		//ȸ�������� �Է��ϴ� �κ�
		public int setMemberInsert(Map<String, Object> map) {
			Object memberKey = insert("account.memberInsert", map);
			return (Integer) memberKey;
		}
		
		//ȸ���������̺� ���ο� ����� ������ �־���.
		public void setMemberRoleInsert(Map<String, Object> map){
			insert("account.memberRoleInsert", map);
		}
		
		//���� ���̵��� ȸ���� ������ ��ȸ
		public Object getIsMember(Map<String, Object> map) {
			return selectOne("account.getIsMember", map);
		}
		
		public void setMemberLoginInfo(Map<String, Object> map){
			update("account.memberLoginInfo", map);
		}
}
