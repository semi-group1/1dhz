package semi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import semi.model.so.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminManagementService adminManagementService;
	private String view;

	public AdminController() {
		view = "admin/common/page";
	}

	@RequestMapping("/user/list")
	public String getAllUserList(Model model) {
		model.addAttribute("command", "userList");
		model.addAttribute("list", adminManagementService.selectAllUserInfos());

		return view;
	}

	@RequestMapping("/user/info")
	public String getUserInfo(Model model, @RequestParam int userId) {
		model.addAttribute("command", "userInfo");
		model.addAttribute("userInfo", adminManagementService.selectUserInfo(userId));

		return view;
	}

	@RequestMapping("/post/list")
	public String getAllPostList(Model model) {
		model.addAttribute("command", "pageList");
		model.addAttribute("list", adminManagementService.selectAllTopics());

		return view;
	}
}