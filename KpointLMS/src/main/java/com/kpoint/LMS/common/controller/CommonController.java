package com.kpoint.LMS.common.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kpoint.LMS.common.common.CommandMap;
import com.kpoint.LMS.common.service.CommonService;

@Controller
@RequestMapping("/common/")
public class CommonController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@RequestMapping("Download.do")
	public void downloadFile(CommandMap commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object>map = commonService.selectFileInfo(commandMap.getMap());
		String fileName = (String) map.get("FILENAME"); //�������� �����̸�
		String fileStrName = (String) map.get("FILESTRNAME"); //����� �����̸�
		String subPath = (String) map.get("BOARDID");
		subPath = "/" + subPath.toLowerCase();
		log.debug("subPath : " + subPath);
		String path  = request.getSession().getServletContext().getRealPath("/upload" + subPath);
		System.out.println("path = " + path);
		String filePath = path + "\\" + fileStrName;
		byte fileByte[] = FileUtils.readFileToByteArray(new File(filePath));
		
		response.setContentType("application/octet-stream");
	    response.setContentLength(fileByte.length);
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName,"UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.getOutputStream().write(fileByte);
	     
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
		
	}
	
	
	@RequestMapping("FileInsert.do")
	@ResponseBody
	public Map<String, Object> setFileInsert(CommandMap commandMap, HttpServletRequest request) throws Exception{
		Map<String, Object> fileList = commonService.setFileInsert(commandMap.getMap(), request);
		return fileList;
	}
	
	@RequestMapping("FileDelete.do")
	@ResponseBody
	public String setFileDelete(CommandMap commandMap, HttpServletRequest request){
		System.out.println("임시파일 지우기");
		commonService.setFileDelete(commandMap.getMap(), request);
		return "Success";
	}
	
	@RequestMapping("FileList.do")
	@ResponseBody
	public List<Map<String, Object>> getFileList(CommandMap commandMap, HttpServletRequest request){
		//commonService.setFileDelete(commandMap.getMap(), request);
		List<Map<String, Object>> fileList = commonService.getFileList(commandMap.getMap());
		return fileList;
	}
	
	
	@RequestMapping("FileListDelete.do")
	@ResponseBody
	public String setFileListDelete(CommandMap commandMap, HttpServletRequest request){
		commonService.setFileListDelete(commandMap.getMap(), request);
		return "Success";
	}
}
