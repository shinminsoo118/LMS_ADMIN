package com.kpoint.LMS.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDAO {
    protected Log log = (Log) LogFactory.getLog(AbstractDAO.class);
     
    @Autowired
    private SqlSessionTemplate sqlSession;
    //protected  Map<String, Object> configMap;
    
    protected void printQueryId(String queryId) {
        if(log.isDebugEnabled()){
            log.debug("\t QueryId  \t:  " + queryId);
        }
    }
     
    public Object insert(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.insert(queryId, params);
    }
     
    public Object update(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.update(queryId, params);
    }
     
    public Object delete(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.delete(queryId, params);
    }
     
    public Object selectOne(String queryId){
        printQueryId(queryId);
        return sqlSession.selectOne(queryId);
    }
     
    public Object selectOne(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.selectOne(queryId, params);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId){
        printQueryId(queryId);
        return sqlSession.selectList(queryId);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.selectList(queryId,params);
    }
    
    @SuppressWarnings("rawtypes")
	public Map getBoardConfig(String queryId, Object bid){
    	 printQueryId(queryId);
    	 return sqlSession.selectOne(queryId, bid);
    }
    
    //페이징와 리스트를 가져오는 부분.
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map selectPagingList(String queryId, Object params){
        printQueryId(queryId);
         
        Map<String,Object> map = (Map<String,Object>)params;
        Map<String, Object> configMap = (Map<String, Object>) map.get("boardConfig"); //보드설정파일을 가져옴.
        Map<String, Object> parameterMap = (Map<String, Object>) map.get("params");
        int totalListCount = (Integer) parameterMap.get("totalListCount");
        Map<String, Object> pageConfigMap = new HashMap<String, Object>();
        
        int listPerPage  = Integer.parseInt(configMap.get("LISTPERPAGE").toString()); //한페이지에 나타날 목록수.
        int pagePerBlock = Integer.parseInt(configMap.get("PAGEPERBLOCK").toString()); //한페이지에 나타날 페이지수
        int currentPageNo; //현재페이지
        
        if(parameterMap.get("currentPageNo") == null){
        	currentPageNo = 1;
        }else{
        	if(parameterMap.get("currentPageNo").toString().equals("")){
        		currentPageNo = 1;
        	}else{
        		currentPageNo = Integer.parseInt(parameterMap.get("currentPageNo").toString());
        	}
        }
        
        String BOARDTBL = configMap.get("BOARDTBL").toString(); //테이블 이름.
        parameterMap.put("BOARDTBL", BOARDTBL);
        //configMap.put("currentPageNo", currentPageNo);
        
        
        //전체 목록수를 가져옴.
        //int totalListCount = sqlSession.selectOne("board.getListCount", parameterMap); //전체목록수
        int totalPageCount = totalListCount / listPerPage + (totalListCount % listPerPage == 0 ? 0 : 1); //전체페이지 수
        
        if(currentPageNo <= 0){
        	currentPageNo = 1;
        }
        
        if(totalPageCount <= 0){
        	totalPageCount = 1;
        }
        
        if(currentPageNo >= totalPageCount){
        	currentPageNo = totalPageCount;
        }
        
        int startPage = (currentPageNo) - (currentPageNo - 1) % pagePerBlock;
        int endPage = startPage + pagePerBlock -1;
        
        if(startPage <= 0){
        	startPage = 1;
        }
        
        if(endPage >= totalPageCount){
        	endPage = totalPageCount;
        }
        
        int startListCount = (currentPageNo - 1) * listPerPage;
        
        //
        /*configMap.put("startPage", startPage);
        configMap.put("endPage", endPage);
        configMap.put("totalListCount", totalListCount);*/
        
        pageConfigMap.put("currentPageNo", currentPageNo);
        pageConfigMap.put("startPage", startPage);
        pageConfigMap.put("endPage", endPage);
        pageConfigMap.put("totalListCount", totalListCount);
        
        parameterMap.put("START", startListCount);
        parameterMap.put("END", listPerPage);
        
        params = parameterMap;
        
        /*System.out.println("**********맵을 찍어보면 *********");
        System.out.println(params);
        System.out.println("*******************************");*/
        
        Map<String,Object> returnMap = new HashMap<String,Object>();
        List<Map<String,Object>> list = sqlSession.selectList(queryId, params);
        
        returnMap.put("boardConfig", configMap);
        returnMap.put("pageConfig", pageConfigMap);
        returnMap.put("result", list);
        
        return returnMap;
    }

}

