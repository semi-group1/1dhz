package semi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}