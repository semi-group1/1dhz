package semi.model;

public class AdminComment {
	private int commentId;
	private int postId;
	private String postTitle;
	private String postCategory;
	private String commentText;
	private int userId;
	private String createdDate;
	private String modifiedDate;
	private String commentStatus;

	public AdminComment() {
	}

	public int getCommentId() {
		return commentId;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public String getCommentText() {
		return commentText;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public int getPostId() {
		return postId;
	}

	public int getUserId() {
		return userId;
	}

	public String getPostCategory() {
		return postCategory;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setPostCategory(String postCategory) {
		this.postCategory = postCategory;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
}
