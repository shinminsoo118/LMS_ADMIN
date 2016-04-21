package com.kpoint.LMS.common.aop;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.kpoint.LMS.common.service.CommonService;
 
@Aspect
public class BoardConfigAspect {
    protected Log log = LogFactory.getLog(BoardConfigAspect.class);
    static String name = "";
    static String type = "";
    
    @Resource(name="commonService")
    private CommonService commonService;
    
    @SuppressWarnings({ "unchecked" })
	@Before("execution(* com.kpoint.LMS..service.*Service.set*(..)) or execution(* com.kpoint.LMS..service.*Service.get*(..)) "
			+ "or execution(* com.kpoint.LMS.member.service.*Service.memberList*(..))")
    public Map<String,Object> boardConfigAspect(JoinPoint joinPoint) throws ClassNotFoundException{
    	System.out.println("************** Aspect 실행 **********");
    	Object[] obj = joinPoint.getArgs();
    	for(int i=0; i < obj.length;i++){
    	}
    	Map<String, Object> map = (Map<String, Object>) obj[0];
    	commonService.boardConfigSetting(map);
    	map.put("params", map);
    	map.put("boardConfig", commonService.boardConfigGetting());

    	return map;
    }
}

