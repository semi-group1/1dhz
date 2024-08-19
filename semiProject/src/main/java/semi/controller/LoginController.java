package semi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	//로그인페이지 이동
	@GetMapping("login/loginPage")
	public String handleSignUp() {
		return "login/loginPage";
	}
	

}
