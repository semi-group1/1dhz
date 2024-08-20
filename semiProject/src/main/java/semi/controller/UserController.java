package semi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import semi.model.dao.UserDao;
import semi.model.dto.UserInfoDto;
import semi.model.so.EditInfoValidation;

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
	
	@RequestMapping(value="/editInfo/editInfoProcess", method=RequestMethod.POST)
	public String editInfoValidation(Model model, UserInfoDto dto) {
		EditInfoValidation validation = new EditInfoValidation();
		boolean isValidate = validation.validation(dto); 
		
		if(isValidate) {
			model.addAttribute("user", userdao.selectOneUser(dto.getId()));
			model.addAttribute("user_post", userdao.selectAllUserPost(dto.getId()));
			return "myPage";
		}else {
			model.addAttribute("user", userdao.selectOneUser(dto.getId()));
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "editInfo";
		}
		
	}
	
}