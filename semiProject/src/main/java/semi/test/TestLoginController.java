package semi.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TestLoginController {

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String handleLogin(HttpSession session) {
		session.setAttribute("userId", 1);
		return "redirect:/semiProject/chatrooms";
	}
}
