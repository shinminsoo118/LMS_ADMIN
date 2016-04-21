package com.kpoint.LMS.board.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class Board {	
	
	@NotEmpty(message="제목을 입력해 주세요.")
	private String TITLE;
	
	@NotEmpty(message="내용을 입력해 주세요.")

	private String CONTENT;
	
	
	public String getTITLE() {
		return TITLE;
	}
	
	public void setTITLE(String TITLE) {
		this.TITLE = TITLE;

	}
	
	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String CONTENT) {
		this.CONTENT = CONTENT;
	}
	
}
