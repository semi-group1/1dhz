package semi.model;

public class MemberForm {
	private int user_id;
	private String user_email;
	private String uesr_pw;
	
	public MemberForm() {
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUesr_pw() {
		return uesr_pw;
	}

	public void setUesr_pw(String uesr_pw) {
		this.uesr_pw = uesr_pw;
	}
}
