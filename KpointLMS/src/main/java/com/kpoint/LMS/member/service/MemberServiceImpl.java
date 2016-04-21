package com.kpoint.LMS.member.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpoint.LMS.common.util.FileUtils;
import com.kpoint.LMS.member.dao.MemberDAO;
import com.kpoint.LMS.member.mail.EmailSender;
import com.kpoint.LMS.member.sms.SendSMS;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Autowired
    private EmailSender emailSender;
	
	@Autowired
    private SendSMS sendSMS;

	@Override
	public Map<String, Object> memberList(Map<String, Object> map) {
		int totalCount = memberDAO.memberTotalCount(map);
		map.put("totalListCount", totalCount);
		return (Map<String,Object>) memberDAO.memberList(map);
	}

	@Override
	public void memberDelete(Map<String, Object> map) {
		memberDAO.memberDelete(map);
		
	}

	@Override
	public Map<String, Object> memberDetail(Map<String, Object> map) {
		return (Map<String,Object>) memberDAO.memberDetail(map);
	}

	@Override
	public void memberUpdate(Map<String, Object> map,HttpServletRequest request) throws Exception{
		if(!map.get("USERIMG").toString().equals("default.png")){
			Map<String,Object> result = fileUtils.profileParseInfo(map, request);
			if(result.size() > 0){
				memberDAO.memberProfileImgUpload(result);
			}
			map.put("USERIMG", result.get("FILESTRNAME"));
		}
		//memberDAO.memberProfileImgUpload(fileUtils.profileParseInfo(map,request));
		memberDAO.memberUpdate(map);
	}

	@Override
	public void memberRoleUpdate(Map<String, Object> map) {
		memberDAO.memberRoleUpdate(map);
	}
	
	@SuppressWarnings("null")
	@Override
	public String setMailSend(Map<String, Object> map) throws Exception{
		
		List<Map<String, Object>> groupList = memberDAO.userGroupList(map);
		
		String[] sendTo = null;
		String[] sendCC = null;
		
		List<String> sendToList =  new ArrayList<String>();
		List<String> sendCCList =  new ArrayList<String>();
		
		List<String> resultToList =  new ArrayList<String>();
		List<String> resultCCList =  new ArrayList<String>();
		
		List<Map<String, Object>> resultGroupToList = null;
		List<Map<String, Object>> resultGroupCCList = null;
		
		String retValue = "";
		memberDAO.setMailInsert(map);
		
		if(map.get("sendTo").toString() != null && map.get("sendTo").toString() != ""){
			sendTo = map.get("sendTo").toString().trim().split(",");
			Collections.addAll(sendToList, sendTo); 
			for(int i = 0; i < sendToList.size(); i++){
				for(int j = 0; j < groupList.size(); j++){
					if(groupList.get(j).get("USERROLE").toString().equals(sendToList.get(i).toString())){
						log.debug("size :" + sendTo.length);
						resultToList.add(sendToList.get(i).trim().toString());
						sendToList.remove(sendToList.get(i).toString());
						
						if(i == 0){
							i--;
							break;
						}
						i--;
						
					}else{
						continue;
					}
				}
			}
			
			if(resultToList.size() > 0){
				map.put("groupSearch", resultToList);
				
				resultGroupToList = memberDAO.groupSearch(map);
				for(int i = 0; i < resultGroupToList.size(); i++){
					sendToList.add(resultGroupToList.get(i).get("USEREMAIL").toString());
				}
			}
			
			if(sendToList.size() > 0){
				map.put("sendToList", sendToList);
			}
		}
		
		if(map.get("sendCC").toString() != null && map.get("sendCC").toString() != "" && map.get("sendCC").toString().length() > 0){
			log.debug("sendCC String" + map.get("sendCC").toString());
			
			sendCC = map.get("sendCC").toString().trim().split(",");
			Collections.addAll(sendCCList, sendCC); 
			for(int i = 0; i < sendCCList.size(); i++){
				for(int j = 0; j < groupList.size(); j++){
					if(groupList.get(j).get("USERROLE").toString().equals(sendCCList.get(i).toString())){
						log.debug("size :" + groupList.get(j));
						resultCCList.add(sendCCList.get(i).trim().toString());
						sendCCList.remove(sendCCList.get(i).toString());
						
						if(i == 0){
							i--;
							break;
						}
						i--;
						
					}else{
						continue;
					}
				}
			}
			
			if(resultCCList.size() > 0){
				map.put("groupSearch", resultCCList);
				
				resultGroupCCList = memberDAO.groupSearch(map);
//				map.put("sendGroupCCList", resultGroupCCList);
				for(int i = 0; i < resultGroupCCList.size(); i++){
					log.debug("CC List" + resultGroupCCList.get(i).get("USEREMAIL").toString());
					sendCCList.add(resultGroupCCList.get(i).get("USEREMAIL").toString());
				}
			}
			
			if(sendCCList.size() > 0){
				map.put("sendCCList", sendCCList);
			}
		}
		log.debug("sendToList.size()" + sendToList.size());
		log.debug("sendCCList.size()" + sendCCList.size());
		if(sendToList.size() > 50 || sendCCList.size() > 50){
			retValue = "cancel";
			return retValue;
		}else{
			retValue = emailSender.SendEmail(map);
		}
		log.debug("size end:" + retValue);
		
		return retValue;
	}
	
	@Override
	public Map<String, Object> getMailList(Map<String, Object> map) {
		int totalCount = memberDAO.mailTotalCount(map);
		map.put("totalListCount", totalCount);
		return (Map<String,Object>) memberDAO.mailList(map);
	} 
	
	@Override
	public Map<String, Object> getSMSList(Map<String, Object> map) {
		int totalCount = memberDAO.smsTotalCount(map);
		map.put("totalListCount", totalCount);
		return (Map<String,Object>) memberDAO.getSMSList(map);
	}
	
	@Override
	public Map<String, Object> getMailDetail(Map<String, Object> map){
		return (Map<String,Object>) memberDAO.mailDetail(map);
	} 
	
	@Override
	public Map<String, Object> getSmsDetail(Map<String, Object> map){
		return (Map<String,Object>) memberDAO.getSmsDetail(map);
	}
	
	@Override
	public List<Map<String, Object>> getUserMailList(Map<String, Object> map){
		
		return memberDAO.userMailList(map);
	}
	
	@Override
	public List<Map<String, Object>> getUserSmsList(Map<String, Object> map){
		
		return memberDAO.getUserSmsList(map);
	}
	
	@Override
	public List<Map<String, Object>> getUserGroupList(Map<String, Object> map){
		
		return memberDAO.userGroupList(map);
	}
	
	@Override
	public String setSmsSend(Map<String, Object> map){
		
		String gourl = "";
		int smsCount = 0;
		
		List<Map<String, Object>> groupList = memberDAO.userGroupList(map);
		
		String[] sendTo = null;
		
		List<String> sendToList =  new ArrayList<String>();
		List<String> resultToList =  new ArrayList<String>();
		List<Map<String, Object>> resultGroupToList = null;
		
		smsCount = memberDAO.setSmsInsert(map);
		
		if(map.get("sendTo").toString() != null && map.get("sendTo").toString() != ""){
			sendTo = map.get("sendTo").toString().trim().split(",");
			Collections.addAll(sendToList, sendTo); 
			for(int i = 0; i < sendToList.size(); i++){
				for(int j = 0; j < groupList.size(); j++){
					if(groupList.get(j).get("USERROLE").toString().equals(sendToList.get(i).toString())){
						resultToList.add(sendToList.get(i).trim().toString());
						sendToList.remove(sendToList.get(i).toString());
						
						if(i == 0){
							i--;
							break;
						}
						i--;
						
					}else{
						continue;
					}
				}
			}
			
			if(resultToList.size() > 0){
				map.put("groupSearch", resultToList);
				
				resultGroupToList = memberDAO.groupSearch(map);
				for(int i = 0; i < resultGroupToList.size(); i++){
					sendToList.add(resultGroupToList.get(i).get("USERTEL").toString());
				}
			}
			
			if(sendToList.size() > 0){
				map.put("sendToList", sendToList);
			}
		}
		
		System.out.println("smsCount : " + smsCount);
		if(sendToList.size() > 0){
			try{
				sendSMS.SendSMS(map);
				gourl = "01"; 								  // 입력값이 null인경우 return 값
			}catch(Exception e){
				log.debug("sendSMS fail :" + e.getMessage());
				gourl = "02";								  // SMS문자 전송중 실패 exception발생경우
			}
			
			if(map.get("RESULT-CODE") != null){
				gourl = map.get("RESULT-CODE").toString(); 	 // 성공 코드 00
				
				if(map.get("CASH") != null){
					if(Integer.parseInt(map.get("CASH").toString()) < 20){
						gourl = "99"; 						// cache가 20원보다 작을 경우 99
					}
				}
			}
			
		}
		return gourl;
	}
}
