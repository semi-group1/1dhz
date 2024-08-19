package semi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import semi.model.EditInfoForm;
import semi.model.dao.UserDao;

@Controller
public class UserController {

	@Autowired
	UserDao userdao;
	
	@RequestMapping("/myPage/{userId}")
	public String myPage(@PathVariable("userId") int userId, Model model) {
		model.addAttribute("user", userdao.selectOneUser(userId));
		model.addAttribute("user_post", userdao.selectAllUserPost(userId));
		
		return "myPage";
	}
	
	@RequestMapping("/editInfo/{userId}")
	public String editInfo(@PathVariable("userId") int userId, Model model) {
		model.addAttribute("user", userdao.selectOneUser(userId));
		
		return "editInfo";
	}
	
	@RequestMapping("/editInfoProcess")
	public String editInfoProcess(@RequestBody EditInfoForm info, Model model) {
		model.addAttribute("editForm", info);
		
		return "editInfoProcess";
	}
}