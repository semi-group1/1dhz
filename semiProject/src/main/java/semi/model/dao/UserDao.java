package semi.model.dao;

import java.sql.*;
import java.util.*;

import org.apache.tomcat.jdbc.pool.DataSource;

import semi.model.User;
import semi.model.User_Post;
import semi.model.dto.UserInfoDto;

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
		this.sql = "select user_id, user_name, user_email, job_category_super, job_category_sub, user_comment, user_profile_image, user_status from semi_user join semi_job_category on semi_user.user_job = semi_job_category.job_category_id where user_id="
				+ id;
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
				user.setUser_status(rs.getString("user_status"));
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

	public List<User_Post> selectAllUserPost(int id) {
		List<User_Post> upList = new ArrayList<User_Post>();
		this.sql = "select post_id, job_category_super, post_title, post_date from semi_user su join semi_topic st on su.user_id = st.user_id join semi_job_category sjc on su.user_job = sjc.job_category_id where su.user_id = "
				+ id;

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				User_Post up = new User_Post();

				up.setPost_id(rs.getInt("post_id"));
				up.setJob_category_super(rs.getString("job_category_super"));
				;
				up.setPost_title(rs.getString("post_title"));
				up.setPost_date(rs.getDate("post_date"));

				upList.add(up);
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

		return upList;
	}

	public boolean updateUserInfo(UserInfoDto dto) {
		int rowNum = 0;

		this.sql = "update semi_user set user_email=?, user_pw=?, user_name=?, user_comment=? where user_id=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getComment());
			pstmt.setInt(5, dto.getId());

			rowNum = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				return false;
			}
		}

		return rowNum > 0;
	}

	public boolean deleteUser(int userId) {
		boolean result = true;

		this.sql = "update semi_user set user_status=? where user_id=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "deleted");
			pstmt.setInt(2, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {

			}

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				result = false;
			}
		}

		return true;
	}
}