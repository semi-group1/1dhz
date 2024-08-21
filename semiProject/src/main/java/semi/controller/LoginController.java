package semi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//
//import semi.model.dao.MemberDao;
//
//@Controller
//public class LoginController {
//	
//	//로그인페이지 이동
//	@RequestMapping("login/loginPage")
//	public String handlelogin() {
//		return "login/loginPage";
//	}
//}
import org.springframework.web.bind.annotation.RequestParam;

import semi.model.Member;
import semi.model.dao.MemberDao;
import java.sql.Connection;
import java.sql.DriverManager;

@Controller
public class LoginController {
	
	@Autowired
    private MemberDao memberDao;
	
   //로그인페이지 이동
   @RequestMapping("login/loginPage")
   public String handlelogin() {
 
      return "login/loginPage";
   }
   
   @PostMapping("/main/mainPage")
   public String processLogin(@RequestParam String email, @RequestParam String password, Model model) {
       // 이메일로 회원 정보를 조회
       Member member = memberDao.findByEmail(email);

       // 회원 정보가 존재하고 비밀번호가 일치하는지 확인
       if (member != null && member.getUser_pw().equals(password)) {
           // 로그인 성공: 세션에 사용자 정보 저장 등
           return "redirect:/main/mainPage";
       } else {
           // 로그인 실패: 오류 메시지 전달
           model.addAttribute("error", "이메일 또는 비밀번호가 잘못되었습니다.");
           return "login/loginPage";
       }
   }
}
