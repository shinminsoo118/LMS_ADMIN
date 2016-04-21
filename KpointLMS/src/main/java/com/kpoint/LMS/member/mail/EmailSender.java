package com.kpoint.LMS.member.mail;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
 
//import com.bean.Email;
 
@Component
public class EmailSender  {
     
    @Autowired
    protected JavaMailSender  mailSender;
 
    @SuppressWarnings({ "null", "unchecked" })
	public String SendEmail(Map<String, Object> map) throws Exception {
//        String[] arr = 
    	
    	String resultType = "";
    	
        MimeMessage msg = mailSender.createMimeMessage();
        
        //true를 세팅함으로써 메일 포맷의 다양화를 지원하겠다는 것을 명시적으로 세팅해준다.
        //MimeMessageHelper helper = new MimeMessageHelper(msg, true,"utf-8");
        resultType = sendMail(map, msg, "TO");
        
        System.out.println("resultType : " + resultType);
        
		return resultType;
         
    }
    
    @SuppressWarnings("unchecked")
	public String sendMail(Map<String, Object> map,MimeMessage msg, String check){
    	
    	String retValue = "";
    	
    	InternetAddress[] toAttrTo = null;
    	InternetAddress[] toAttrCC = null;
    	
    	List<String> sendTo = null;
    	List<String> sendCC = null;
    	
    	if((List<String>)map.get("sendToList") != null){
    		sendTo = (List<String>)map.get("sendToList");
    		toAttrTo = new InternetAddress[sendTo.size()];
    	}
    	
    	if((List<String>)map.get("sendCCList") != null){
    		sendCC = (List<String>)map.get("sendCCList");
    		toAttrCC = new InternetAddress[sendCC.size()];
    	}
    	
    	try{
        	if(sendTo != null){
            	for(int i =0; i < sendTo.size(); i++){
//            		System.out.println("sendTo : " + sendTo.get(i));
            		toAttrTo[i] = new InternetAddress (sendTo.get(i).toString());
            	}
            }
        	
        	if(sendCC != null){
            	for(int i =0; i < sendCC.size(); i++){
//            		System.out.println("sendCC : " + sendCC.get(i));
            		toAttrCC[i] = new InternetAddress (sendCC.get(i).toString());
            	}
            }
        	
            msg.setSubject(map.get("TITLE").toString());
            msg.setText(map.get("CONTENT").toString());
            
            if(sendTo != null){
            	msg.setRecipients(RecipientType.TO, toAttrTo);
            }
            if(sendCC != null){
            	msg.setRecipients(RecipientType.CC, toAttrCC);
            }
            
            mailSender.send(msg); 
            retValue = "success";
    	}catch(Exception e){
    		retValue = "fail";
    		System.out.println("====================================FAILED" + e.getMessage());
    	}
      
    	return retValue;
    }
    
}
