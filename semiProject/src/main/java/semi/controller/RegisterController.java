package semi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
	
	@RequestMapping("/register/signUpPage")
	public String handleSignUp() {
		return "register/signUpPage";
	}
}
