package semi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import semi.model.so.*;
import semi.model.*;

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
		model.addAttribute("userPosts", adminManagementService.selectAllUserPosts(userId));
		model.addAttribute("userComments", adminManagementService.selectAllUserComments(userId));

		return view;
	}

	@GetMapping("/user/inactivate")
	public String showInactiveWindow(Model model, @RequestParam int userId) {
		model.addAttribute("command", "inactivate");
		model.addAttribute("userInfo", adminManagementService.selectUserInfo(userId));
		return view;
	}

	@PostMapping("/user/inactivate")
	public String setUserIactivate(Model model, AdminUserInactive form) {
		System.out.println(form);
		return view;
	}

	@RequestMapping("/topic/list/all")
	public String getAllPostList(Model model) {
		model.addAttribute("command", "topicListAll");
		model.addAttribute("list", adminManagementService.selectAllTopics());

		return view;
	}

	@RequestMapping("/topic/list/general")
	public String getGeneralTopics(Model model) {
		model.addAttribute("command", "topicListGeneral");
		model.addAttribute("list", adminManagementService.selectGeneralTopics());

		return view;
	}

	@RequestMapping("/topic/list/job")
	public String getJobTopics(Model model) {
		model.addAttribute("command", "topicListJob");
		model.addAttribute("list", adminManagementService.selectJobTopics());

		return view;
	}
}