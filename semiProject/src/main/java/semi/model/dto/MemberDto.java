package semi.model.dto;
import java.util.Date;

public class MemberDto {

	private int user_id;
	private String user_email;
	private String user_pw;
	private Date join_date;
	private String user_role;

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

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public Date Join_date() {
		return join_date;
	}

	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	@Override
	public String toString() {
	return "MemberDTO [userid=" + user_id + ", email=" + user_email + ", pw=" + user_pw + ", join_date=" + join_date + ", user_role=" + user_role + "]";
	 }
	}
