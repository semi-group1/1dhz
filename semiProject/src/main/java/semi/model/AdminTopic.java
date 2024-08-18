package semi.model;

public class AdminTopic {
	private int postId;
	private int postCategoryId;
	private String postCategoryName;
	private String postTitle;
	private String postCreatedDate;
	private String postModifiedDate;
	private int postUserId;
	private String postUserName;
	private AdminPostStatus postStatus;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getPostCategoryId() {
		return postCategoryId;
	}

	public void setPostCategoryId(int postCategoryId) {
		this.postCategoryId = postCategoryId;
	}

	public String getPostCategoryName() {
		return postCategoryName;
	}

	public void setPostCategoryName(String postCategoryName) {
		this.postCategoryName = postCategoryName;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostCreatedDate() {
		return postCreatedDate;
	}

	public void setPostCreatedDate(String postCreatedDate) {
		this.postCreatedDate = postCreatedDate;
	}

	public String getPostModifiedDate() {
		return postModifiedDate;
	}

	public void setPostModifiedDate(String postModifiedDate) {
		this.postModifiedDate = postModifiedDate;
	}

	public int getPostUserId() {
		return postUserId;
	}

	public void setPostUserId(int postUserId) {
		this.postUserId = postUserId;
	}

	public String getPostUserName() {
		return postUserName;
	}

	public void setPostUserName(String postUserName) {
		this.postUserName = postUserName;
	}

	public AdminPostStatus getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(AdminPostStatus postStatus) {
		this.postStatus = postStatus;
	}

	public void setPostStatus(String string) {
		this.postStatus = AdminPostStatus.valueOf(string);
	}
}
