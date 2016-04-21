package com.kpoint.LMS.member.membervo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class MemberUpdateVO {
	@NotNull(message="NAME을 입력해 주세요.")
	@Size(min=1, max=30, message="NAME 한 글자 이상 입력해 주세요.")
	private String username;
	@NotNull(message="성을 입력해 주세요.")
	@Size(min=1, max=30, message="USERSEX 한 글자 이상 입력해 주세요.")
	private String usersex;
	@Email(message="Email을 올바르게 입력해 주세요.")
	@Size(min=1, max=30, message="Email 한 글자 이상 입력해 주세요.")
	private String useremail;
	@NotNull(message="휴대폰 번호를 입력해 주세요.")
	@Size(min=1, max=30, message="휴대폰 번호 한 글자 이상 입력해 주세요.")
	private String usertel;
	@NotNull(message="유저 그룹을 입력해 주세요.")
	@Size(min=1, max=30, message="유저 그룹 한 글자 이상 입력해 주세요.")
	private String userrole;
	@NotNull(message="주소를 입력해 주세요.")
	@Size(min=1, max=50, message="주소 한 글자 이상 입력해 주세요.")
	private String useraddr;
	
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsersex() {
		return usersex;
	}
	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUsertel() {
		return usertel;
	}
	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}
	public String getUseraddr() {
		return useraddr;
	}
	public void setUseraddr(String useraddr) {
		this.useraddr = useraddr;
	}
}
