package semi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import semi.model.User;
import semi.model.dao.HelloDao;
import semi.model.dto.UserInfoDto;
import semi.model.so.UserService;

@Controller
public class UserController {
	
	@Autowired
	HelloDao dao;
	
	@Autowired
	UserService us;
	
	@RequestMapping("/myPage/{userId}")
	public String myPage(@PathVariable("userId") int userId, Model model) {
		User user = us.selectOneUser(userId);
		if(!user.getUser_status().equals("active")) {
			model.addAttribute("msg", "테스트 입니다.");
			model.addAttribute("emp", dao.selectAll());
			return "hello";
		}else {
			model.addAttribute("user", user);
			model.addAttribute("user_post", us.selectAllUserPost(userId));
			
			return "myPage";
		}
	}
	
	@RequestMapping("/editInfo/{userId}")
	public String editInfo(@PathVariable("userId") int userId, Model model) {
		model.addAttribute("user", us.selectOneUser(userId));
		
		return "editInfo";
	}
	
	@RequestMapping("/userDelete/{userId}")
	public String deleteUser(@PathVariable("userId") int userId, Model model) {
		if(us.isDeleteSuccess(userId)) {
			return "toMain";
		}else {
			model.addAttribute("user", us.selectOneUser(userId));
			model.addAttribute("user_post", us.selectAllUserPost(userId));
			return "myPage";
		}
		
		
	}
	
	@RequestMapping(value="/editInfo/editInfoProcess", method=RequestMethod.POST)
	public String editInfoValidation(Model model, UserInfoDto dto) {		
		if(us.isEditValidate(dto)) {			
			if(us.isEditSuccess(dto)) {
				model.addAttribute("user", us.selectOneUser(dto.getId()));
				model.addAttribute("user_post", us.selectAllUserPost(dto.getId()));
				return "myPage";
			}else {
				model.addAttribute("user", us.selectOneUser(dto.getId()));
				model.addAttribute("msg", "데이터 수정 오류");
				return "editInfo";
			}
		}else {
			model.addAttribute("user", us.selectOneUser(dto.getId()));
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "editInfo";
		}
	}
	
}