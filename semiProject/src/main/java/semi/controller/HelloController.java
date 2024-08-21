package semi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import semi.model.AdminUser;
import semi.model.AdminUserRole;
import semi.model.dao.HelloDao;

@Controller
public class HelloController {

	@Autowired
	HelloDao dao;

	@RequestMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("msg", "테스트 입니다.");
		model.addAttribute("emp", dao.selectAll());

		return "hello";
	}

	@RequestMapping("/adminTest")
	public String test(HttpSession session) {
		return "admin/adminTest";
	}

	@RequestMapping("/loginAdmin")
	public String loginAdmin(HttpSession session) {
		AdminUser loginUser = new AdminUser();
		loginUser.setUserRole(AdminUserRole.admin);

		session.setAttribute("loginUser", loginUser);
		return "admin/adminTest";
	}

	@RequestMapping("/logoutAdmin")
	public String logoutAdmin(HttpSession session) {
		session.invalidate();

		return "admin/adminTest";
	}
}