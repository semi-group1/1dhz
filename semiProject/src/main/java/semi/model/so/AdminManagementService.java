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

	public AdminManagementService() {
	}

	public List<AdminUser> selectAllUserInfos() {
		return adminUserDAO.selectAllUserInfos();
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

	public List<AdminTopic> selectGeneralTopics() {
		return adminTopicDAO.selectGeneralTopics();
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
		AdminReportType statusType = null;

		switch (type) {
		case "post":
			statusType = AdminReportType.post;
			break;
		case "user":
			statusType = AdminReportType.user;
			break;
		case "comment":
			statusType = AdminReportType.comment;
			break;
		}

		adminReports = adminReportDAO.selectReportsByType(statusType);

		return adminReports;
	}

	public boolean updateReportStatus(int reportId, String status) {
		boolean result = false;
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

		if (adminReportDAO.updateReportStatus(reportId, statusType) == 1) {
			result = true;
		}

		return result;
	}
}