package semi.model;

public class AdminInactive {
	private int userId;
	private int postId;
	private String type;
	private String desc;
	private int length;

	public AdminInactive() {
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

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
