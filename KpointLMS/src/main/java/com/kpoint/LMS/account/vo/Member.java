package com.kpoint.LMS.account.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class Member {
	
	@NotNull(message="���̵� �Է��� �ּ���.")
	@Size(min=4, max=20, message="���̵�� �ּ� 4�ڸ� �̻� �ִ�20�ڸ� ���� �Դϴ�.")
	private String USERID;
	
	@NotNull(message="�̸��� �Է��� �ּ���.")
	private String USERNAME;
	
	@NotNull(message="��й�ȣ�� �Է��� �ּ���.")
	@Size(min=4, max=20, message="��й�ȣ�� �ּ� 4�ڸ� �̻� �ִ�20�ڸ� ���� �Դϴ�.")
	private String USERPW;
	
	@NotNull(message="��ȭ��ȣ�� �Է��� �ּ���.")
	private String USERTEL;
	
	@Email(message="�ùٸ� �̸����ּҸ� �Է��� �ּ���.")
	private String USEREMAIL;
	
	private String USERADDR;
	private String USERIMG;
	private String USERREGDATE;
	private String USERSEX;
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String USERNAME) {
		this.USERNAME = USERNAME;
	}
	public String getUSERPW() {
		return USERPW;
	}
	public void setUSERPW(String USERPW) {
		this.USERPW = USERPW;
	}
	public String getUSERTEL() {
		return USERTEL;
	}
	public void setUSERTEL(String USERTEL) {
		this.USERTEL = USERTEL;
	}
	public String getUSEREMAIL() {
		return USEREMAIL;
	}
	public void setUSEREMAIL(String USEREMAIL) {
		this.USEREMAIL = USEREMAIL;
	}
	public String getUSERADDR() {
		return USERADDR;
	}
	public void setUSERADDR(String USERADDR) {
		this.USERADDR = USERADDR;
	}
	public String getUSERIMG() {
		return USERIMG;
	}
	public void setUSERIMG(String USERIMG) {
		this.USERIMG = USERIMG;
	}
	public String getUSERREGDATE() {
		return USERREGDATE;
	}
	public void setUSERREGDATE(String USERREGDATE) {
		this.USERREGDATE = USERREGDATE;
	}
	public String getUSERSEX() {
		return USERSEX;
	}
	public void setUSERSEX(String USERSEX) {
		this.USERSEX = USERSEX;
	}
	
	
}
