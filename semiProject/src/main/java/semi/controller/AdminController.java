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

	@RequestMapping("")
	public String setDefaultPage() {
		return "redirect: admin/user/list";
	}

	@RequestMapping("/user/list")
	public String getAllUserList(Model model, @RequestParam(required = false) String page) {
		if (page == null) {
			page = "1";
		}
		model.addAttribute("command", "userList");
		model.addAttribute("page", page);
		model.addAttribute("maxPage", ams.getMaxPage(ams.countAllUser()));
		model.addAttribute("list", ams.selectAllUserInfos(Integer.parseInt(page)));

		return view;
	}

	@RequestMapping("/user/listInactive")
	public String getInactiveUserList(Model model, @RequestParam(required = false) String page) {
		if (page == null) {
			page = "1";
		}
		model.addAttribute("command", "userListInactive");
		model.addAttribute("page");
		model.addAttribute("maxPage", ams.getMaxPage(ams.countInactiveUser()));
		model.addAttribute("list", ams.selectInactiveUsers(Integer.parseInt(page)));

		return view;
	}

	@RequestMapping("/user/search")
	public String findUserByKeyword(Model model, @RequestParam(required = false) String page,
			@RequestParam String keyword, @RequestParam String type) {
		if (page == null) {
			page = "1";
		}

		model.addAttribute("command", "userSearchList");
		model.addAttribute("keyword", keyword);
		model.addAttribute("type", type);
		model.addAttribute("page", page);
		model.addAttribute("maxPage", ams.getMaxPage(ams.countByKeyword(keyword, type)));
		model.addAttribute("list", ams.selectByKeyword(Integer.parseInt(page), keyword, type));

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
	public String getAllPostList(Model model, @RequestParam(required = false) String page) {
		if (page == null) {
			page = "1";
		}
		model.addAttribute("command", "topicListAll");
		model.addAttribute("page", page);
		model.addAttribute("maxPage", ams.getMaxPage(ams.countAllTopic()));
		model.addAttribute("list", ams.selectAllTopics(Integer.parseInt(page)));

		return view;
	}

	@RequestMapping("/topic/listGeneral")
	public String getGeneralTopics(Model model, @RequestParam(required = false) String page) {
		if (page == null) {
			page = "1";
		}
		model.addAttribute("command", "topicListGeneral");
		model.addAttribute("page", page);
		model.addAttribute("maxPage", ams.getMaxPage(ams.countGeneralTopic()));
		model.addAttribute("list", ams.selectGeneralTopics(Integer.parseInt(page)));
		model.addAttribute("categories", ams.selectGeneralCategories());

		return view;
	}

	@RequestMapping("/topic/selectCategory")
	public String getTopicsByCategory(Model model, int categoryId, @RequestParam(required = false) String page) {
		if (page == null) {
			page = "1";
		}
		model.addAttribute("command", "topicListGeneral");
		model.addAttribute("page", page);
		model.addAttribute("maxPage", ams.getMaxPage(ams.countByCategoryId(categoryId)));
		model.addAttribute("list", ams.selectTopicsByCategory(Integer.parseInt(page), categoryId));
		model.addAttribute("categories", ams.selectGeneralCategories());
		model.addAttribute("categoryId", categoryId);

		return view;
	}

	@RequestMapping("/topic/listJob")
	public String getJobTopics(Model model, @RequestParam(required = false) String page) {
		if (page == null) {
			page = "1";
		}
		model.addAttribute("command", "topicListJob");
		model.addAttribute("page", page);
		model.addAttribute("maxPage", ams.getMaxPage(ams.countJobTopics()));
		model.addAttribute("list", ams.selectJobTopics(Integer.parseInt(page)));
		model.addAttribute("categories", ams.selectJobCategories());

		return view;
	}

	@RequestMapping("/topic/selectJobCategory")
	public String getTopicsByJobCategory(Model model, int categoryId, @RequestParam(required = false) String page) {
		if (page == null) {
			page = "1";
		}
		model.addAttribute("command", "topicListJob");
		model.addAttribute("page", page);
		model.addAttribute("maxPage", ams.getMaxPage(ams.countByJobCategoryId(categoryId)));
		model.addAttribute("list", ams.selectByJobCategoryId(Integer.parseInt(page), categoryId));
		model.addAttribute("categories", ams.selectJobCategories());
		model.addAttribute("categoryId", categoryId);

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