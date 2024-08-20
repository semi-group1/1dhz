package semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import semi.model.Member_Login;
import semi.model.Member_SignUp;
import semi.model.dao.MemberDao;

@Controller
public class MemberController {
	
//	@Autowired
//	private MemberAuthService memberAuthSvc;
	
	public MemberController() {
	}

//    @Autowired
//    MemberDao memberDao;

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @RequestMapping("/signUpPage")
    public String signUpPage() {
        return "signUpPage";
    }

//    @PostMapping("/login/loginProcess")
//	public String login(semi.model.LoginRequest login, HttpSession session) {
//		
//		if(this.memberAuthSvc.checkLogin(login)) {
//			login.setPassword("");
//			session.setAttribute("auth", login);
//			return "redirect:/mainPage";
//		}
//		else {
//			session.setAttribute("loginFailMsg", "로그인에 실패했습니다. 다시 시도해주세요.");
//			return "redirect:/login/loginPage";
//		}
//	}
//
//    @PostMapping("/signUpProcess")
//    public String signUpProcess(@RequestBody Member_SignUp signUpForm, Model model) {
//    	
//        
//        return "signUpProcess";
//    }
}
	
	
	

