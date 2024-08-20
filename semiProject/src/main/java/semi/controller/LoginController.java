package semi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	//로그인페이지 이동
	@RequestMapping("login/loginPage")
	public String handleSignUp() {
		return "login/loginPage";
	}
	

}
