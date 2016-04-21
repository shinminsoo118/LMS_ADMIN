package com.kpoint.LMS.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
 
@Component("fileUtils")
public class FileUtils {
	
	public Map<String, Object> profileParseInfo(Map<String,Object> map,HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		String filePath = request.getSession().getServletContext().getRealPath("/upload");
		
		File file = new File(filePath);
		if(file.exists() == false){
			file.mkdirs();
		}
		MultipartFile uploadFile = null;
		String originalFileExtension = null;
		String storedFileName = null;
		String originalFileName = null;
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		while(iterator.hasNext()){
			
			uploadFile = multipartHttpServletRequest.getFile(iterator.next());
			
			if(uploadFile.isEmpty() == false){
				originalFileName = uploadFile.getOriginalFilename();
				
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString() + originalFileExtension;
				filePath += "\\memberIcon\\" + storedFileName;
				
				System.out.println("fileName : " + originalFileName + ", type : " + originalFileExtension + ",storedFileName : "+storedFileName+",filepath : "+filePath);
				
				file = new File(filePath);
				uploadFile.transferTo(file);
				
				result.put("USERID", map.get("USERID"));
				result.put("FILESIZE", uploadFile.getSize());
				result.put("FILENAME", originalFileName);
				result.put("FILESTRNAME", storedFileName);
				
			}
			else{
				System.out.println("USERIMG : "+map.get("USERIMG"));
				result.put("FILESTRNAME",map.get("USERIMG"));
			}
		}
		
		return result;
	}
	
    public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
        
        String subPath = (String)map.get("subFilePath");
        
        String filePath = request.getSession().getServletContext().getRealPath("/upload" + subPath);
        System.out.println("file path =" + filePath);
        
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
         
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null; 
         
        String boardSeq = (String)map.get("SEQ");
        String bid  = (String)map.get("bid");
        String writer = (String) map.get("FILEWRITER"); 
        
        File file = new File(filePath);
        if(file.exists() == false){
            file.mkdirs();
        }
         
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            if(multipartFile.isEmpty() == false){
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = CommonUtils.getRandomString() + originalFileExtension;
                 
                file = new File(filePath + "\\" + storedFileName);
                multipartFile.transferTo(file);
                
                listMap = new HashMap<String,Object>();
                listMap.put("BOARDID", bid);
                listMap.put("BOARDSEQ", boardSeq);
                listMap.put("FILEWRITER", writer);
                listMap.put("FILENAME", originalFileName);
                listMap.put("FILESTRNAME", storedFileName);
                listMap.put("FILESIZE", multipartFile.getSize());
                list.add(listMap);
            }
        }
        return list;
    }
    
    public void setFileDelete(List<Map<String, Object>> map, HttpServletRequest request, String subPath){
    	
    	String filePath = request.getSession().getServletContext().getRealPath("/upload" + subPath);
    	if(map.size() > 0){
    		for(int i= 0; i<map.size(); i++){
        		Map<String, Object> fileName = map.get(i);
        		String deleteFile = fileName.get("FILESTRNAME").toString();
    	    	String deleteFileName = filePath + "\\" + deleteFile;
    	    	File file = new File(deleteFileName);
    	        if(file.exists()){
    	            file.delete();
    	        }
        	}
    	}
    }
    
    public void setFileDeletes(List<Map<String, Object>> map, HttpServletRequest request,String subPath){
    	
    	String filePath = request.getSession().getServletContext().getRealPath("/upload" + subPath);
    	if(map.size() > 0){
    		for(int i= 0; i<map.size(); i++){
        		Map<String, Object> fileName = map.get(i);
        		String deleteFile = fileName.get("FILESTRNAME").toString();
    	    	String deleteFileName = filePath + "\\" + deleteFile;
    	    	File file = new File(deleteFileName);
    	        if(file.exists()){
    	            file.delete();
    	        }
        	}
    	}
    }
    
    public void setOneFileDelete(String fileName, HttpServletRequest request,String subPath){
    	String filePath = request.getSession().getServletContext().getRealPath("/upload" + subPath);
    	String deleteFileName = filePath + "\\" + fileName;
    	File file = new File(deleteFileName);
        if(file.exists()){
            file.delete();
        }
    }
    
    public String setSubFilePath(String filePath){
    	String boardPath = "";
		switch (filePath) {
			case "Document": boardPath = "/document";  break;
			case "Notice"  : boardPath = "/notice";    break;
			case "Curriculumn"  : boardPath = "/curriculumn";    break;
	//		case to board add
			default: boardPath = "";				   break;
		}    	
    	return boardPath;
    }
}

