package semi.model.so;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import semi.model.dao.*;
import semi.model.*;

@Service
public class AdminManagementService {
	@Autowired
	AdminUserDAO adminUserDAO;
	@Autowired
	AdminTopicDAO adminTopicDAO;
	@Autowired
	AdminReportDAO adminReportDAO;

	public int getStartNum(int page) {
		return (page - 1) * 20 + 1;
	}

	public int getEndNum(int page) {
		return (page - 1) * 20 + 20;
	}

	public int getMaxPage(int count) {
		return (count - 1) / 20 + 1;
	}

	public AdminManagementService() {
	}

	public List<AdminUser> selectAllUserInfos() {
		return adminUserDAO.selectAllUserInfos();
	}

	public List<AdminUser> selectAllUserInfos(int page) {
		return adminUserDAO.selectAllUserInfos(this.getStartNum(page), this.getEndNum(page));
	}

	public List<AdminUser> selectByKeyword(int page, String keyword, String type) {
		return adminUserDAO.selectByKeyword(this.getStartNum(page), this.getEndNum(page), keyword, type);
	}

	public int countAllUser() {
		return adminUserDAO.countAllUser();
	}

	public int countByKeyword(String keyword, String type) {
		return adminUserDAO.countByKeyword(keyword, type);
	}

	public List<AdminUser> selectInactiveUsers() {
		return adminUserDAO.selectInactiveUsers();
	}

	public List<AdminUser> selectInactiveUsers(int page) {
		return adminUserDAO.selectInactiveUsers(this.getStartNum(page), this.getEndNum(page));
	}

	public int countInactiveUser() {
		return adminUserDAO.countInactiveUser();
	}

	public AdminUser selectUserInfo(int userId) {
		return adminUserDAO.selectUserInfo(userId);
	}

	public List<AdminTopic> selectAllUserPosts(int userId) {
		return adminUserDAO.selectAllUserPosts(userId);
	}

	public List<AdminComment> selectAllUserComments(int userId) {
		return adminUserDAO.selectAllUserComments(userId);
	}

	@Transactional
	public boolean setUserInactive(AdminInactive form) {
		int result = 0;

		if (adminUserDAO.isUserInactive(form.getUserId()) > 0) {
			return false;
		}

		result = adminUserDAO.insertUserInactive(form)
				* adminUserDAO.updateUserStatus(form.getUserId(), AdminUserStatus.inactive);

		return result == 1;
	}

	public List<AdminTopic> selectAllTopics() {
		return adminTopicDAO.selectAllTopics();
	}

	public List<AdminTopic> selectAllTopics(int page) {
		return adminTopicDAO.selectAllTopics(this.getStartNum(page), this.getEndNum(page));
	}

	public int countAllTopic() {
		return adminTopicDAO.countAllTopic();
	}

	public List<AdminCategory> selectGeneralCategories() {
		return adminTopicDAO.selectGeneralCategories();
	}

	public List<AdminTopic> selectTopicsByCateogry(int categoryId) {
		return adminTopicDAO.selectByCategoryId(categoryId);
	}

	public List<AdminTopic> selectGeneralTopics() {
		return adminTopicDAO.selectGeneralTopics();
	}

	public List<AdminTopic> selectTopicByJobCateogry(int categoryId) {
		return adminTopicDAO.selectByJobCategoryId(categoryId);
	}

	public List<AdminCategory> selectJobCategories() {
		return adminTopicDAO.selectJobCategories();
	}

	public List<AdminTopic> selectJobTopics() {
		return adminTopicDAO.selectJobTopics();
	}

	@Transactional
	public boolean setPostInactive(AdminInactive form) {
		int result = 0;

		if (adminTopicDAO.isPostInactive(form.getPostId()) > 0) {
			return false;
		}

		result = adminTopicDAO.insertPostInactive(form)
				* adminTopicDAO.updatePostStatus(form.getPostId(), AdminPostStatus.inactive);

		return result == 1;
	}

	public List<AdminReport> selectReportsByType(String type) {
		List<AdminReport> adminReports = null;

		switch (type) {
		case "post":
			adminReports = adminReportDAO.selectPostReports();
			break;
		case "user":
			adminReports = adminReportDAO.selectUserReports();
			break;
		case "comment":
			adminReports = adminReportDAO.selectCommentReports();
			break;
		}

		return adminReports;
	}

	public boolean updateReportStatus(int reportId, String status) {
		AdminReportStatus statusType = null;

		switch (status) {
		case "inProgress":
			statusType = AdminReportStatus.inProgress;
			break;
		case "completed":
			statusType = AdminReportStatus.completed;
			break;
		case "denied":
			statusType = AdminReportStatus.denied;
			break;
		}

		return adminReportDAO.updateReportStatus(reportId, statusType) == 1;
	}
}