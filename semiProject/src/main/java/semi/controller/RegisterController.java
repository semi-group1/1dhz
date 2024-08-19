package semi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
	
	//회원가입페이지 이동
	@GetMapping("/register/signUp")
	public String handleSignUp(Model model) {
		return "register/signUp";
	}
}
