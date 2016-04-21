package com.kpoint.LMS.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kpoint.LMS.common.dao.CommonDAO;
import com.kpoint.LMS.common.util.FileUtils;

@Service("commonService")
public class CommonServiceImpl implements CommonService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commonDAO")
	private CommonDAO commonDAO;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	private Map<String, Object> boardConfig = null;
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return commonDAO.selectFileInfo(map);
	}
	
	@Override
	public Map<String, Object> setFileInsert(Map<String, Object> map, HttpServletRequest request) throws Exception {
		
		Map<String, Object> maxSeq = commonDAO.getMaxFileSeq(map);
		int maxFileSql = 0;
		
		String boardPath = "";
		
		String boardPathMatch = (String)boardConfig.get("BOARDID");
		boardPath = fileUtils.setSubFilePath(boardPathMatch);
//		String boardPathMatch = (String)boardConfig.get("BOARDID");
//		switch (boardPathMatch) {
//			case "Document": boardPath = "/document";  break;
//			case "Notice"  : boardPath = "/notice";  break;
//	//		case to board add
//			default: boardPath = "";					break;
//		}
		map.put("subFilePath", boardPath);
		setFiles(map, request);
		
		System.out.println("FILE SEQ = "+ maxSeq);
		if(maxSeq != null){
			maxFileSql = (Integer) maxSeq.get("FILESEQ");
		}
		map.put("MAXFILESEQ", maxFileSql);
		List<Map<String, Object>> tempFileList = commonDAO.getTempFileList(map);
//		String path = "upload" + boardPath;
		String path = "upload" + boardPath;
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("tempFileList", tempFileList);
		returnMap.put("path", path);
		return returnMap;
	}
	
	@Override
	public void setFiles(Map<String, Object> map, HttpServletRequest request) throws Exception {
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0, size=list.size(); i<size ; i++){
			commonDAO.setFileInsert(list.get(i));
			//throw new RuntimeException();
		}
	}

	@Override
	public void setFileDelete(Map<String, Object> map, HttpServletRequest request) {
		
		String boardPath = "";
		
		String boardPathMatch = (String)boardConfig.get("BOARDID");
		boardPath = fileUtils.setSubFilePath(boardPathMatch);
		List<Map<String, Object>> fileNull = commonDAO.selectFileListNull(map);
	
		if(fileNull.size() > 0){
			fileUtils.setFileDelete(fileNull, request, boardPath);
			commonDAO.setFileDeleteNull(map);
		}
		
		List<Map<String, Object>> fileList = commonDAO.selectFileList(map);
		log.debug("==============================");
		fileUtils.setFileDelete(fileList, request, boardPath);
		commonDAO.setFileDelete(map);
	}

	@Override
	public List<Map<String, Object>> getFileList(Map<String, Object> map) {
		return commonDAO.getFileLists(map);
	}

	@Override
	public void boardConfigSetting(Map<String, Object> map) {
		System.out.println("======= BoardConfigSetting =======");

		System.out.println("boardConfig = " + boardConfig);
		System.out.println("bid = " + map.get("bid"));
		if(boardConfig != null){
			System.out.println("boardid  = " + boardConfig.get("BOARDID"));
		}
		
		if(boardConfig == null || !boardConfig.get("BOARDID").equals(map.get("bid"))){
			//boardConfig가 null 이거나 파라미터 값과 boardConfig의 BOARDID 값이 다른경우 
			boardConfig = commonDAO.boardConfig(map);
			if(boardConfig == null){
				//commonDAO.boardConfig값이 null인경우 존재하지 않는 게시판.
				//NullPointerException 발생.
				throw new NullPointerException("noBoard");
			}else{
				map.put("BOARDTBL", boardConfig.get("BOARDTBL"));
			}
		}else{
			//boardConfig 가 null 아니고, 파라미터 값과 BOARDID값이 같으면 기존값 사용.
			map.put("BOARDTBL", boardConfig.get("BOARDTBL"));
		}
	}

	@Override
	public Map<String, Object> boardConfigGetting(){
		return boardConfig;
	}
	
	//에디터에서 파일 삭제버튼을 눌렀을때 실행되는 부분.
	@Override
	public void setFileListDelete(Map<String, Object> map, HttpServletRequest request) {
		
		String boardPath = "";
		
		String boardPathMatch = (String)boardConfig.get("BOARDID");
		boardPath = fileUtils.setSubFilePath(boardPathMatch);
		
		log.debug("TTTTTTTTTTTTTTTTT :" + boardPath);
		//String type = (String) map.get("type");
		//System.out.println("type= "+ type);
		/*if(type.equals("All")){
			//전체파일 삭제일경우.
			String[] fileList = (String[]) map.get("fileName[]");
			System.out.println("fileList 파일개수 = " + fileList.length);
			for(String fileName : fileList){
				commonDAO.setFileListDelete(fileName);
				fileUtils.setOneFileDelete(fileName, request);
			}
		}else{*/
			//단일파일 삭제일경우.
			String fileName = (String) map.get("fileName");
			System.out.println("fileName = " + fileName);
			commonDAO.setFileListDelete(fileName);
			fileUtils.setOneFileDelete(fileName, request, boardPath);
		//}
	}
}
