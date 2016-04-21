package com.kpoint.LMS.account.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

public class UserDetailsVO extends User{
	
	
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	private String userEmail;
	private String userName;
	private String userImg;
	
	public UserDetailsVO(String username, String password, boolean enabled, 
						 boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
						 Collection<? extends GrantedAuthority> authorities, 
						 String userEmail, String userName, String userImg) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		System.out.println("username vo = " + username);
		this.userEmail = userEmail;
		this.userName = userName;
		this.userImg = userImg;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

}
