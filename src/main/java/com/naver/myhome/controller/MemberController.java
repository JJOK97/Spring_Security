package com.naver.myhome.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.myhome.domain.Member;
import com.naver.myhome.service.MemberService;

/*
    @Component�� �̿��ؼ� ������ �����̳ʰ� �ش� Ŭ���� ��ü�� �����ϵ��� ������ �� ������
    ��� Ŭ������ @Component�� �Ҵ��ϸ� � Ŭ������ � ������ �����ϴ��� �ľ��ϱ�
    ��ƽ��ϴ�. ������ �����ӿ�ũ������ �̷� Ŭ�������� �з��ϱ� ���ؼ�
    @Component�� ����Ͽ� ������ ���� �� ���� �ֳ����̼��� �����մϴ�.
    
    1. @Controller - ������� ��û�� �����ϴ� Controller Ŭ����
    2. @Repository - ������ ���̽� ������ ó���ϴ� DAOŬ����
    3. @Service - ����Ͻ� ������ ó���ϴ� Service Ŭ����
 */

//@Controller
//@RequestMapping(value="/member")//http://localhost:8088/myhome4/member/�� �����ϴ� �ּ� ����
public class MemberController {
   //import org.slf4j.Logger;
   //import org.slf4j.LoggerFactory;
   private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
   
   private MemberService memberService;
   private PasswordEncoder passwordEncoder;
   
   @Autowired
   public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
      this.memberService = memberService;
      this.passwordEncoder = passwordEncoder;
   }
   
   /*
    @CookieValue(value="saveid", required=false) Cookie readCookie
    �̸��� saveid�� ��Ű�� Cookie Ÿ������ ���޹޽��ϴ�.
    ������ �̸��� ��Ű�� �������� ���� ���� �ֱ� ������ required=false �� �����ؾ� �մϴ�.
    �� id ����ϱ⸦ �������� ���� ���� �ֱ� ������ required=false �� �����ؾ� �մϴ�.
    required=true ���¿��� ������ �̸��� ���� ��Ű�� �������� ������ ������ MVC�� �ͼ����� �߻���ŵ�ϴ�.
    */
   // http://localhost:8088/myhome4/member/login
   // �α��� ���̵�
   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public ModelAndView login(ModelAndView mv, @CookieValue(value="saveid", required=false) Cookie readCookie) {
      if(readCookie != null) {
         mv.addObject("saveid",readCookie.getValue());
         logger.info("cookie time=" + readCookie.getMaxAge());
      }
         mv.setViewName("member/member_loginForm");
         return mv;
   }
   
   // http://localhost:8088/myhome4/member/login
   // ȸ������ �� �̵�
   @RequestMapping(value = "/join", method = RequestMethod.GET)
   public String join() {
      return "member/member_joinForm"; //WEB-INF/views/member/member_joinForm.jsp
   }
   
   //ȸ������������ ���̵� �˻�
   @ResponseBody    // @ResponseBody�� �̿��ؼ� �� �޼����� ���� ����� JSON���� ��ȯ�Ǿ�
               		//HTTP Response BODY�� �����˴ϴ�.
   @RequestMapping(value = "/idcheck", method = RequestMethod.GET)
   public int idcheck(@RequestParam("id") String id) {
      return memberService.isId(id);
   }
   
   @RequestMapping(value = "/joinProcess", method = RequestMethod.POST)
   public String joinprocess(Member m, RedirectAttributes rattr,
		   					 Model model,
		   					 HttpServletRequest request) {
	   String encPassword = passwordEncoder.encode(m.getPassword());
	   logger.info(encPassword);
	   m.setPassword(encPassword);
	   
	   int result = memberService.insert(m);
	   
	   //result = 0;
	   /*
	    ���������� �����ϴ� RedirectAttributes�� ������ Servlet���� ���Ǵ�
	    response.sendRedirect()�� ����� ���� ������ �뵵�� ����մϴ�.
	    �����̷�Ʈ�� �����ϸ� �Ķ���͸� �����ϰ��� �� �� addAttribute()�� addFlashAttribute()�� ����մϴ�.
	    ��) response.sendRedirect("/teset?result=1");
	    => rattr.addAttribute("result",1)
	    */
	   
	   //���Ե� ���
	   if(result == 1) {
		   rattr.addFlashAttribute("result","joinSuccess");
		   return "redirect:login";
	   } else {
		   model.addAttribute("url", request.getRequestURL());
		   model.addAttribute("messsage", "ȸ�� ���� ����");
		   return "error/error";
	   }
			  
   }
   
   //�α��� ó��
   @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
   public String loginProcess(
                        @RequestParam("id") String id,
                        @RequestParam("password") String password,
                        @RequestParam(value="remember", defaultValue="", required=false)
                                   String remember,
                                   HttpServletResponse response,
                                   HttpSession session,
                                   RedirectAttributes rattr) {
	   
      int result = memberService.isId(id, password);
      
      logger.info("��� : " + result);
      
      if (result == 1) {
         //�α��� ����
         session.setAttribute("id", id);
         Cookie savecookie = new Cookie("saveid",id);
         if(!remember.equals("")) {
            savecookie.setMaxAge(60*60);
            logger.info("��Ű���� : 60*60");
         }else {
            logger.info("��Ű���� : 0");
            savecookie.setMaxAge(0);
         }
         response.addCookie(savecookie);
         
         return "redirect:/board/list";
      } else {
    	  rattr.addFlashAttribute("result", result);
    	  return "redirect:login";
      }
	
   }
   
   @GetMapping(value = "update")
   public ModelAndView Memberupdate(
         ModelAndView mv, 
         HttpServletRequest request,
         HttpSession session
         ) {
	   
      Member memberinfo = memberService.member_info((String)session.getAttribute("id"));

      if (memberinfo == null) {
         logger.info("�������� ����");
         mv.setViewName("error/error");
         mv.addObject("url", request.getRequestURL());
         mv.addObject("message", "�������� �����Դϴ�.");
         return mv;
      } 
      logger.info("(����)�󼼺��� ����");
      // ���� �� �������� �̵��� �� ���� �� ������ �����ֱ� ������ boarddata ��ü��
      // ModelAndView ��ü�� �����մϴ�.
      mv.addObject("memberinfo", memberinfo);
      // �� ���� �� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
      mv.setViewName("member/member_update");
      return mv;
   }
   
   @RequestMapping(value="updateProcess")
   public String BoardUpdateProcess(
		   Member member,
		   Model mv,
		   HttpServletRequest request,
		   RedirectAttributes rattr) throws Exception {
	   
	   int result = memberService.update(member);
	   String url = "";
	   
	   if ( result == 1) {
		   rattr.addFlashAttribute("result","updateSuccess");
		   url = "redirect:/board/list";
	   } else {
		   mv.addAttribute("url", request.getRequestURL());
		   mv.addAttribute("message","���� ���� ����");
		   url = "error/error";
	   } 
	   
	   return url;
   }
   
   @RequestMapping(value = "/memberList")
   public ModelAndView memberList(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
		   						  @RequestParam(value = "limit", defaultValue = "3", required = false) int limit,
		   						  @RequestParam(value = "search_field", defaultValue = "-1", required = false) int index,
		   						  @RequestParam(value = "search_word", defaultValue = "", required = false ) String search_word,
		                           ModelAndView mv) {
	   int listcount = memberService.getSearchListCount(index, search_word); // �� ����Ʈ ���� �޾ƿ�
	   List<Member> list = memberService.getSearchList(index,  search_word,  page,  limit);
	   
	   // �� ������ �� 
	   int maxpage = (listcount + limit - 1) / limit;
	   
	   // ���� �������� ������ ���� ������ �� (1, 11, 21 ��...)
	   int startpage = ((page - 1) / 10) * 10 + 1;

	   // ���� �������� ������ ������ ������ �� (10, 20, 30 ��...)
	   int endpage = startpage + 10 - 1;

	   if (endpage > maxpage)
	       endpage = maxpage;

	   mv.setViewName("member/member_list");
	   mv.addObject("page", page);
	   mv.addObject("maxpage", maxpage);
	   mv.addObject("startpage", startpage);
	   mv.addObject("endpage", endpage);
	   mv.addObject("listcount", listcount);
	   mv.addObject("memberlist", list);
	   mv.addObject("search_field", index);
	   mv.addObject("search_word", search_word);

	   return mv;
	   
   }
   
   @RequestMapping(value="info")
   public ModelAndView memberInfo( 
		   String id, ModelAndView mv,
		   HttpServletRequest request) {
	   
	   Member info = memberService.member_info(id);

	      if (info == null) {
	         logger.info("�󼼺��� ����");
	         mv.setViewName("error/error");
	         mv.addObject("url", request.getRequestURL());
	         mv.addObject("message", "�󼼺��� �����Դϴ�.");
	         return mv;
	      } 
	      logger.info("�󼼺��� ����");
	      mv.addObject("memberinfo", info);
	      mv.setViewName("member/member_info");
	      return mv;
   }
   
   @RequestMapping("/delete")
   public String MemberDeleteAction(String id ) {
	   
	  memberService.delete(id);
	   
      return "redirect:memberList";
   }
   
   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String loginout(HttpSession session) {
	   session.invalidate();
	   return "redirect:login";
   }
  
}
