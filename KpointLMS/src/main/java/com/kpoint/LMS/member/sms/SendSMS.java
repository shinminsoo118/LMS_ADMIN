package com.kpoint.LMS.member.sms;

import org.springframework.stereotype.Component;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SendSMS {

	 @SuppressWarnings({ "unchecked", "unused" })
	public void SendSMS( Map<String, Object> map ){
		 
		 List<String> sendTo = null;
		 String number[] = null;
		 
		 int doneCount = 0;
		 int failCount = 0;
		 if(map.get("sendToList") != null){
			 sendTo = (List<String>)map.get("sendToList");
			 sendTo = (List<String>)map.get("sendToList");
		 }
		 
	 	 SMS sms = new SMS();
        
         sms.appversion("TEST/1.0");
         sms.charset("utf8");
         sms.setuser("", "");				// coolsms 계정 입력해주시면되요
        
//         sendTo = null;
         
         if(sendTo != null){
        	 number = new String[sendTo.size()];               // 받을 사람 폰번호
        	 
        	 for(int i = 0; i < sendTo.size(); i ++){
            	 number[i] = sendTo.get(i).toString().replaceAll("-", "");
//            	 System.out.println(i + "번째 :" +number[i]);
             }
         }else{
        	 return;
         }
         
         
         // coolsms 사이트 가서 회원가입 후 충천
         for( int i = 0 ; i < number.length ; i ++ ) {
//        	 System.out.println(i + "번째 :" +number[i]);
	         SmsMessagePdu pdu = new SmsMessagePdu();
	         pdu.type = "SMS";
	         pdu.destinationAddress = number[i];
	         pdu.scAddress = "01090025882"	;                  				// 발신자 번호          
	         pdu.text = map.get("CONTENT").toString();					    // 보낼 메세지 내용
	         sms.add(pdu);
	
	         try {
	             sms.connect();
	             sms.send();
	             HashMap<String,String> resultMap = sms.remain();
	             sms.credits();
	             sms.disconnect();
	             map.put("RESULT-MESSAGE", resultMap.get("RESULT-MESSAGE").toString());
            	 map.put("RESULT-CODE", resultMap.get("RESULT-CODE").toString());
            	 map.put("CASH", resultMap.get("CASH").toString());
            	 
            	 if(Integer.parseInt(map.get("CASH").toString()) < 20){
            		 break;
            	 }
            	 
            	 if("Success".equals(resultMap.get("RESULT-MESSAGE").toString())){
            		 doneCount++;
            	 }else{
            		 failCount++;
            	 }
            	 
//				  지연            	 
//            	 try {
//					Thread.sleep(1);
//				 } catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				 }
	         } catch (IOException e) {
	            System.out.println(e.toString());
	         }
	         sms.printr();
	         sms.emptyall();
	     }
         
         //전송성공 단말
         map.put("doneCount", doneCount);
         //전송실패 단말
         map.put("failCount", failCount);
     }
}
