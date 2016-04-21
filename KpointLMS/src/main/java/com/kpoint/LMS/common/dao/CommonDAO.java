package com.kpoint.LMS.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("commonDAO")
public class CommonDAO extends AbstractDAO{
		
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectFileInfo(Map<String, Object> map) {
		return (Map<String, Object>)selectOne("common.selectFileInfo", map);
	}
	
	//
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTempFileList(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("common.getTempFileList", map);
	}
	
	public void setFileInsert(Map<String, Object> map) {
		insert("common.setFileInsert", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFileList(Map<String, Object> map) {
		List<Map<String, Object>> fileNames = selectList("common.getFileLists", map);
		return fileNames;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFileListNull(Map<String, Object> map) {
		List<Map<String, Object>> fileNames = selectList("common.getFileListNull", map);
		return fileNames;
	}

	public void setFileDelete(Map<String, Object> map) {
		delete("common.setFileDelete", map);
	}
	
	public void setFileDeleteNull(Map<String, Object> map) {
		delete("common.setFileNullDelete", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFileLists(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("common.getFileLists", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> boardConfig(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("common.getBoardConfig", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMaxFileSeq(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("common.getTempMaxFileSeq", map);
	}
	
	public void setFileListDelete(String fileName) {
		delete("common.setFileListDelete", fileName);
	}
	
	//파일업로드한 내용을 업데이트 함.
	public void setFileUpdate(Map<String, Object> map) {
		update("common.setFileUpdate", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFileNames(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("common.getFileNames", map);
	}
	
	//수정페이지에서 파일이름과 관련된 요소를 지우기 위해 파일 이름을 가져옴.
	@SuppressWarnings("unchecked")
	public Map<String, Object> getFileName(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("common.getFileName", map);
	}
	
	//게시글과 관련된 파일삭제
	public void setOneFileDelete(Map<String, Object> map) {
		delete("common.setOneFileDelete", map);
	}
}
