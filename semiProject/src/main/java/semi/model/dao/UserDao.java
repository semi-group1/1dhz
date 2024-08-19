package semi.model.dao;

import java.sql.*;
import java.util.*;

import org.apache.tomcat.jdbc.pool.DataSource;

import semi.model.User;

public class UserDao {
	private DataSource ds;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	public UserDao() {
	}

	public UserDao(DataSource ds) {
		this.ds = ds;
	}
	
	public User selectOneUser(int id) {
		this.sql = "select user_id, user_name, user_email, job_category_super, job_category_sub, user_comment, user_profile_image from semi_user join semi_job_category on semi_user.user_job = semi_job_category.job_category_id where user_id=" + id;
		User user = new User();
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_job(rs.getString("job_category_super") + "<br />" + rs.getString("job_category_sub"));
				user.setUser_comment(rs.getString("user_comment"));
				user.setUser_profile_image(rs.getString("user_profile_image"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return user;
	}

	public List<User> selectAllUser() {
		this.sql = "select * from semi_user order by user_id";
		List<User> userList = new ArrayList<User>();

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				User u = new User();

				u.setUser_id(rs.getInt("user_id"));
				u.setUser_name(rs.getString("user_name"));
				u.setUser_email(rs.getString("user_email"));

				userList.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return userList;
	}
}
