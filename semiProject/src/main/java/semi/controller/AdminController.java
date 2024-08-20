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
	private AdminManagementService ams;
	private String view;

	public AdminController() {
		view = "admin/common/page";
	}

	@RequestMapping("/user/list")
	public String getAllUserList(Model model) {
		model.addAttribute("command", "userList");
		model.addAttribute("list", ams.selectAllUserInfos());

		return view;
	}

	@RequestMapping("/user/listInactive")
	public String getInactiveUserList(Model model) {
		model.addAttribute("command", "userListInactive");
		model.addAttribute("list", ams.selectInactiveUsers());

		return view;
	}

	@RequestMapping("/user/info")
	public String getUserInfo(Model model, @RequestParam int userId) {
		model.addAttribute("command", "userInfo");
		model.addAttribute("userInfo", ams.selectUserInfo(userId));
		model.addAttribute("userPosts", ams.selectAllUserPosts(userId));
		model.addAttribute("userComments", ams.selectAllUserComments(userId));

		return view;
	}

	@GetMapping("/user/inactivate")
	public String showUserInactiveWindow(Model model, @RequestParam int userId) {
		model.addAttribute("command", "inactivateUser");
		model.addAttribute("userInfo", ams.selectUserInfo(userId));
		return view;
	}

	@PostMapping("/user/inactivate")
	public String setUserInactive(Model model, AdminInactive form) {
		if (ams.setUserInactive(form)) {
			System.out.println("회원이 비활성화 되었습니다.");
		} else {
			System.out.println("회원이 비활성화 되지 않았습니다.");
		}
		return "redirect: info?userId=" + form.getUserId();
	}

	@RequestMapping("/topic/listAll")
	public String getAllPostList(Model model) {
		model.addAttribute("command", "topicListAll");
		model.addAttribute("list", ams.selectAllTopics());

		return view;
	}

	@RequestMapping("/topic/listGeneral")
	public String getGeneralTopics(Model model) {
		model.addAttribute("command", "topicListGeneral");
		model.addAttribute("list", ams.selectGeneralTopics());

		return view;
	}

	@RequestMapping("/topic/listJob")
	public String getJobTopics(Model model) {
		model.addAttribute("command", "topicListJob");
		model.addAttribute("list", ams.selectJobTopics());

		return view;
	}

	@GetMapping("/topic/inactivate")
	public String showPostInactiveWindow(Model model, @RequestParam int postId) {
		model.addAttribute("command", "inactivatePost");
		model.addAttribute("postId", postId);
		return view;
	}

	@PostMapping("/topic/inactivate")
	public String setPostInactive(Model model, AdminInactive form) {
		System.out.println(form.getPostId() + " / " + form.getType() + form.getDesc());
		if (ams.setPostInactive(form)) {
			System.out.println("게시글이 비활성화 되었습니다.");
		} else {
			System.out.println("게시글이 비활성화 되지 않았습니다.");
		}
		return "redirect: listAll";
	}

	@RequestMapping("/report/post")
	public String getPostReports(Model model) {
		model.addAttribute("command", "postReports");
		model.addAttribute("list", ams.selectReportsByType("post"));
		return view;
	}

	@RequestMapping("/report/user")
	public String getUserReports(Model model) {
		model.addAttribute("command", "userReports");
		model.addAttribute("list", ams.selectReportsByType("user"));
		return view;
	}

	@RequestMapping("/report/comment")
	public String getCommentReports(Model model) {
		model.addAttribute("command", "commentReports");
		model.addAttribute("list", ams.selectReportsByType("comment"));
		return view;
	}

	@RequestMapping("/report/changeStatus")
	public String changeStatus(Model model, @RequestParam int reportId, @RequestParam String status,
			@RequestParam String reportType) {
		String redirectView = "redirect: ";

		if (ams.updateReportStatus(reportId, status)) {
			System.out.println("접수된 신고가 처리되었습니다.");
		} else {
			System.out.println("접수된 신고가 처리되지 않았습니다.");
		}

		return redirectView += reportType;
	}
}