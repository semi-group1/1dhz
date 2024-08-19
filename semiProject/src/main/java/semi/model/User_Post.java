package semi.model;

import java.util.Date;

public class User_Post {
	
	private int post_id;
	private String job_category_super;
	private String post_title;
	private Date post_date;
	
	public void User_Post() {
	}
	
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getJob_category_super() {
		return job_category_super;
	}
	public void setJob_category_super(String job_category_super) {
		this.job_category_super = job_category_super;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	
}
