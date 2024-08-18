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

	@Transactional
	public boolean setUserInactive(int userId, String desc, int length) {
		boolean result = false;

		if (adminUserDAO.isUserInactive(userId) == 1) {
			return result;
		}

		if (adminUserDAO.insertUserInactive(userId, desc, length) == 1
				&& adminUserDAO.updateUserStatus(userId, AdminUserStatus.inactive) == 1) {
			result = true;
		}

		return result;
	}

	public List<AdminTopic> selectAllTopics() {
		return adminTopicDAO.selectAllTopics();
	}

	@Transactional
	public boolean setPostInactive(int postId, String desc) {
		boolean result = false;

		if (adminTopicDAO.isPostInactive(postId) == 1) {
			return false;
		}

		if (adminTopicDAO.insertPostInactive(postId, desc) == 1
				&& adminTopicDAO.updatePostStatus(postId, AdminPostStatus.inactive) == 1) {
			result = true;
		}

		return result;
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