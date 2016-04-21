package com.kpoint.LMS.classroom.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kpoint.LMS.common.common.CommandMap;

@Controller
@RequestMapping("/administrator/classroom/")
public class ClassRoomController {
	Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("classProcessRoomList.do")
	public String classProceeRoomList(CommandMap commandMap){
		return "administrator.classroom.classProcessRoomList";
	}
	
	@RequestMapping("classProcessRoomDetail.do")
	public String classProcessRoomDetail(CommandMap commandMap){
		return "administrator.classroom.classProcessRoomDetail";
	}
	
	@RequestMapping("classEndRoomList.do")
	public String classEndRoomList(CommandMap commandMap){
		return "administrator.classroom.classEndRoomList";
	}
	
	@RequestMapping("classEndRoomDetail.do")
	public String classEndRoomDetail(CommandMap commandMap){
		return "administrator.classroom.classEndRoomDetail";
	}
	
//	@RequestMapping("lectureDetail.do")
//	public String getLectureDetail(CommandMap commandMap){
//		return "administrator.lecture.lectureDetail";
//	}
	
	
}