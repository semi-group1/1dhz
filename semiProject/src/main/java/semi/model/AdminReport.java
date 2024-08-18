package semi.model;

public class AdminReport {
	private int repordId;
	private String reportType;
	private int reportTargetId;
	private int reportUserId;
	private String reportUserName;
	private String reportDesc;
	private String reportedDate;
	private AdminReportStatus reportStatus;

	public int getRepordId() {
		return repordId;
	}

	public void setRepordId(int repordId) {
		this.repordId = repordId;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public int getReportTargetId() {
		return reportTargetId;
	}

	public void setReportTargetId(int reportTargetId) {
		this.reportTargetId = reportTargetId;
	}

	public int getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(int reportUserId) {
		this.reportUserId = reportUserId;
	}

	public String getReportDesc() {
		return reportDesc;
	}

	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}

	public String getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}

	public AdminReportStatus getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(AdminReportStatus reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getReportUserName() {
		return reportUserName;
	}

	public void setReportUserName(String reportUserName) {
		this.reportUserName = reportUserName;
	}
}