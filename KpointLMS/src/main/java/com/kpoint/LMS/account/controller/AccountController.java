package com.kpoint.LMS.account.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kpoint.LMS.account.service.AccountService;
import com.kpoint.LMS.common.common.CommandMap;
import com.kpoint.LMS.common.util.ShaEncoder;
import com.kpoint.LMS.account.vo.Member;

@Controller
public class AccountController {
	Logger log = Logger.getLogger(this.getClass());
	
	/*sha 256 ��ȣȭ*/
	@Resource(name="shaEncoder")
	private ShaEncoder shaEncoder;
	
	@Resource(name="accountService")
	private AccountService accountService;
	
	//�α����� �����Ͽ�����.
	@RequestMapping("/loginSuccess.do")
	public String loginSuccess(HttpSession session,CommandMap commandMap, Principal principal, Authentication auth){
		//UserDetailsVO vo = (UserDetailsVO) auth.getPrincipal();
		
		String goPage = "redirect:/index.do";
		String loginInfo = (String) principal.getName();
		
		commandMap.put("loginInfo", loginInfo);
		accountService.memberLoginInfo(commandMap.getMap());
		
		for(GrantedAuthority gauth : auth.getAuthorities()){
			log.debug("=====================");
			log.debug(gauth.getAuthority());
			if(gauth.getAuthority().equals("ROLE_ADMIN") || gauth.getAuthority().equals("ROLE_SUPERADMIN") || gauth.getAuthority().equals("ROLE_USER")){
				goPage = "redirect:/administrator/index.do";
			}
		}
		
		/*SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();*/
		//String goPage = "/index.do";
		/*session.setAttribute("loginInfo", memberService.userLoginInfo());
		for (GrantedAuthority auth : authentication.getAuthorities()) {
            if(auth.getAuthority().equals("ROLE_SUPERADMIN") || auth.getAuthority().equals("ROLE_ADMIN")){
            	goPage ="redirect:/administrator/index.do";
            }
        }*/
		
		System.out.println("goPage" + goPage);
		return goPage;
	}
	
	//�α׾ƿ� ���� ���
	@RequestMapping("/logOut.do")
	public String logOut(Authentication auth){
		System.out.println("aaaaa" + auth.getPrincipal());
		return "redirect:/index.do";
	}
	
	@RequestMapping("/register.do")
	public String memberInsert(CommandMap commandMap, @ModelAttribute("memberVo") @Valid Member memberVo, BindingResult bindingResult, Model model){
		//String userPw = shaEncoder.encoding(commandMap.get("USERPW").toString());
		System.out.println("�̰����� ������?");
		//���ε� �� ��ȿ��üũ
		if(bindingResult.hasErrors()){
			model.addAttribute("memberVo", memberVo);
			return "front.index";
		}
		
		String userId = (String) commandMap.get("USERID");
		String userPw = (String) commandMap.get("USERPW");
		//salt Encoding
		String saltPw = shaEncoder.saltEncoding(userPw, userId);
		commandMap.put("USERPW", saltPw);
		
		//ȸ�������� �Է��Ҷ� ���������� ����ó������ �����Ͽ� �������� �Ķ���� ������.
		Object returnType = accountService.memberInsert(commandMap.getMap());
		
		String goPage = "redirect:/index.do?error=1";
		
		if(returnType == null){
			goPage = "redirect:/index.do?error=join";
		}
		
		return goPage;
	}
}
