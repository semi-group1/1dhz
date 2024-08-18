package semi.model.dao;

import java.sql.*;
import java.util.*;

import org.apache.tomcat.jdbc.pool.DataSource;

import semi.model.*;

public class AdminUserDAO {
	private DataSource ds;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	public AdminUserDAO() {
	}

	public AdminUserDAO(DataSource ds) {
		this.ds = ds;
	}

	public List<AdminUser> selectAllUserInfos() {
		List<AdminUser> adminUsers = new ArrayList<AdminUser>();
		this.sql = """
				select user_id, user_name, user_email
				from semi_user
				order by user_id
				""";

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				AdminUser adminUser = new AdminUser();

				adminUser.setUserId(rs.getInt("user_id"));
				adminUser.setUserName(rs.getString("user_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return adminUsers;
	}

	public AdminUser selectUserInfo(int userId) {
		AdminUser adminUser = new AdminUser();
		this.sql = """
				SELECT user_id, user_name, user_email, job_category_super, job_category_sub, user_status, to_char(user_join_date, 'YYYY-MM-DD') user_join_date
				FROM SEMI_USER su JOIN SEMI_JOB_CATEGORY sjc ON su.USER_JOB = sjc.JOB_CATEGORY_ID
				WHERE user_id = ?;
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				adminUser.setUserId(rs.getInt("user_id"));
				adminUser.setUserName(rs.getString("user_name"));
				adminUser.setUserJob(rs.getString("job_category_super") + ">" + rs.getString("job_category_sub"));
				adminUser.setUserStatus(AdminUserStatus.valueOf(rs.getString("user_status")));
				adminUser.setUserJoinDate("user_join_date");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return adminUser;
	}

	public int updateUserStatus(int userId, AdminUserStatus status) {
		int rowCount = 0;
		this.sql = """
				update semi_user
				set user_status = ?
				where user_id = ?
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status.toString());
			pstmt.setInt(2, userId);
			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}

	public int insertUserInActive(int userId, String desc, int length) {
		int rowCount = 0;
		this.sql = """
				insert into semi_user_inactive
				values(?, ?, sysdate, sysdate + ?)
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, desc);
			pstmt.setInt(3, length);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

	public int isUserInActive(int userId) {
		int count = 0;
		this.sql = """
				SELECT COUNT(*) AS user_count
				FROM SEMI_USER_INACTIVE sui
				WHERE sui.INACTIVE_USER_ID = ? AND SYSDATE BETWEEN sui.INACTIVE_START_DATE AND sui.INACTIVE_END_DATE
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("user_count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}
}