package semi.model;

public class AdminUserInactive {
	private int userId;
	private String desc;
	private int length;

	public AdminUserInactive() {
	}

	public String getDesc() {
		return desc;
	}

	public int getLength() {
		return length;
	}

	public int getUserId() {
		return userId;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return userId + "/" + desc + "/" + length;
	}
}
