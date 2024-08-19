package semi.model;

import java.util.Date;

public class User {
	
	private int user_id;
	private String user_name;
	private String user_email;
	private String user_pw;
	private String user_job;
	private String user_comment;
	private String user_profile_image;
	private String user_status;
	private Date user_join_date;
	private Date user_out_date;
	private String user_role;
	
	public void User() {
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_job() {
		return user_job;
	}
	public void setUser_job(String user_job) {
		this.user_job = user_job;
	}
	public String getUser_comment() {
		return user_comment;
	}
	public void setUser_comment(String user_comment) {
		this.user_comment = user_comment;
	}
	public String getUser_profile_image() {
		return user_profile_image;
	}
	public void setUser_profile_image(String user_profile_image) {
		this.user_profile_image = user_profile_image;
	}
	public String getUser_status() {
		return user_status;
	}
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	public Date getUser_join_date() {
		return user_join_date;
	}
	public void setUser_join_date(Date user_join_date) {
		this.user_join_date = user_join_date;
	}
	public Date getUser_out_date() {
		return user_out_date;
	}
	public void setUser_out_date(Date user_out_date) {
		this.user_out_date = user_out_date;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	
}
