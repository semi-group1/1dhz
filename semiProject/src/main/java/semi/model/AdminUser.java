package semi.model;

public class AdminUser {
	private int userId;
	private String userName;
	private String userJob;
	private AdminUserStatus userStatus;
	private String userJoinDate;
	private String userOutDate;
	private AdminUserRole userRole;
	private int userReportedCount;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserJob() {
		return userJob;
	}

	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}

	public AdminUserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(AdminUserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserJoinDate() {
		return userJoinDate;
	}

	public void setUserJoinDate(String userJoinDate) {
		this.userJoinDate = userJoinDate;
	}

	public String getUserOutDate() {
		return userOutDate;
	}

	public void setUserOutDate(String userOutDate) {
		this.userOutDate = userOutDate;
	}

	public AdminUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(AdminUserRole userRole) {
		this.userRole = userRole;
	}

	public int getUserReportedCount() {
		return userReportedCount;
	}

	public void setUserReportedCount(int userReportedCount) {
		this.userReportedCount = userReportedCount;
	}

}