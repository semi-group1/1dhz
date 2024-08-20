package semi.model.dao;

import java.sql.*;
import java.util.*;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.model.*;

@Repository
public class AdminUserDAO {

	@Autowired
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

	public void setPaging() {
		this.sql = """
				select *
				from (select rownum as rn, t.* from (
				""" + this.sql + """
				) t)
				where rn between ? and ?
				""";
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
				AdminUser au = new AdminUser();

				au.setUserId(rs.getInt("user_id"));
				au.setUserName(rs.getString("user_name"));
				au.setUserEmail(rs.getString("user_email"));

				adminUsers.add(au);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return adminUsers;
	}

	public int countAllUser() {
		int rowNum = 0;
		this.sql = """
				select count(*) as count from semi_user
				""";
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				rowNum = rs.getInt("count");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rowNum;
	}

	public List<AdminUser> selectAllUserInfos(int startNum, int endNum) {
		List<AdminUser> adminUsers = new ArrayList<AdminUser>();
		this.sql = """
				select user_id, user_name, user_email
				from semi_user
				order by user_id
				""";

		this.setPaging();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminUser au = new AdminUser();

				au.setUserId(rs.getInt("user_id"));
				au.setUserName(rs.getString("user_name"));
				au.setUserEmail(rs.getString("user_email"));

				adminUsers.add(au);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return adminUsers;
	}

	public int countByKeyword(String keyword, String type) {
		int rowNum = 0;
		try {
			conn = ds.getConnection();
			this.sql = """
					select count(*) as count
					from semi_user
					""";
			switch (type) {
			case "userId":
				sql += "where user_id = ?";
				break;
			case "userName":
				sql += "where user_name like ?";
				break;
			case "userEmail":
				sql += "where user_email = ?";
				break;
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				rowNum = rs.getInt("count");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowNum;
	}

	public List<AdminUser> selectByKeyword(int startNum, int endNum, String keyword, String type) {
		List<AdminUser> adminUsers = new ArrayList<AdminUser>();
		try {
			conn = ds.getConnection();
			this.sql = """
					select user_id, user_name, user_email
					from semi_user
					""";
			switch (type) {
			case "userId":
				sql += "where user_id = ?";
				break;
			case "userName":
				sql += "where user_name like ?";
				break;
			case "userEmail":
				sql += "where user_email = ?";
				break;
			}
			sql += """
					order by user_id
					""";
			this.setPaging();

			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminUser au = new AdminUser();

				au.setUserId(rs.getInt("user_id"));
				au.setUserName(rs.getString("user_name"));
				au.setUserEmail(rs.getString("user_email"));

				adminUsers.add(au);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return adminUsers;
	}

	public List<AdminUser> selectInactiveUsers() {
		List<AdminUser> adminUsers = new ArrayList<AdminUser>();
		this.sql = """
				SELECT
					user_id,
					user_name,
					inactive_desc,
					to_char(inactive_start_date, 'YYYY-MM-DD') as inactive_start_date,
					to_char(inactive_end_date, 'YYYY-MM-DD') as inactive_end_date
				FROM
					SEMI_USER su
				JOIN SEMI_USER_INACTIVE sui ON
					su.USER_ID = sui.inactive_user_id
				ORDER BY
					inactive_start_date DESC
				""";

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				AdminUser au = new AdminUser();

				au.setUserId(rs.getInt("user_id"));
				au.setUserName(rs.getString("user_name"));
				au.setUserInactiveDesc(rs.getString("inactive_desc"));
				au.setUserInactiveStartDate(rs.getString("inactive_start_date"));
				au.setUserInactiveEndDate(rs.getString("inactive_end_date"));

				adminUsers.add(au);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return adminUsers;
	}

	public int countInactiveUser() {
		int rowNum = 0;
		this.sql = """
				SELECT
					count(*)
				FROM
					SEMI_USER su
				JOIN SEMI_USER_INACTIVE sui ON
					su.USER_ID = sui.inactive_user_id
				""";
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				rowNum = rs.getInt("count");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rowNum;
	}

	public List<AdminUser> selectInactiveUsers(int startNum, int endNum) {
		List<AdminUser> adminUsers = new ArrayList<AdminUser>();
		this.sql = """
				SELECT
					user_id,
					user_name,
					inactive_desc,
					to_char(inactive_start_date, 'YYYY-MM-DD') as inactive_start_date,
					to_char(inactive_end_date, 'YYYY-MM-DD') as inactive_end_date
				FROM
					SEMI_USER su
				JOIN SEMI_USER_INACTIVE sui ON
					su.USER_ID = sui.inactive_user_id
				ORDER BY
					inactive_start_date DESC
				""";

		this.setPaging();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminUser au = new AdminUser();

				au.setUserId(rs.getInt("user_id"));
				au.setUserName(rs.getString("user_name"));
				au.setUserInactiveDesc(rs.getString("inactive_desc"));
				au.setUserInactiveStartDate(rs.getString("inactive_start_date"));
				au.setUserInactiveEndDate(rs.getString("inactive_end_date"));

				adminUsers.add(au);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
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
		AdminUser au = new AdminUser();
		this.sql = """
				SELECT user_id, user_name, user_email, job_category_super, job_category_sub, user_status, to_char(user_join_date, 'YYYY-MM-DD') user_join_date
				FROM SEMI_USER su JOIN SEMI_JOB_CATEGORY sjc ON su.USER_JOB = sjc.JOB_CATEGORY_ID
				WHERE user_id = ?
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				au.setUserId(rs.getInt("user_id"));
				au.setUserName(rs.getString("user_name"));
				au.setUserEmail(rs.getString("user_email"));
				au.setUserJob(rs.getString("job_category_super") + ">" + rs.getString("job_category_sub"));
				au.setUserStatus(AdminUserStatus.valueOf(rs.getString("user_status")));
				au.setUserJoinDate(rs.getString("user_join_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return au;
	}

	public List<AdminTopic> selectAllUserPosts(int userId) {
		List<AdminTopic> atList = new ArrayList<AdminTopic>();
		AdminTopic at;
		this.sql = """
				SELECT post_id, post_title, to_char(post_date, 'YYYY-MM-DD') post_date, (select count(*) from semi_comment where post_id = ?) as post_comments
				FROM SEMI_TOPIC st
				JOIN SEMI_TOPIC_CATEGORY stc ON st.CATE_NO = stc.TOPIC_CATEGORY_ID
				JOIN SEMI_USER su ON st.USER_ID = su.USER_ID
				WHERE st.user_id = ?
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				at = new AdminTopic();

				at.setPostId(rs.getInt("post_id"));
				at.setPostTitle(rs.getString("post_title"));
				at.setPostCreatedDate(rs.getString("post_date"));
				at.setPostComments(rs.getInt("post_comments"));

				atList.add(at);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return atList;
	}

	public List<AdminComment> selectAllUserComments(int userId) {
		List<AdminComment> acList = new ArrayList<AdminComment>();
		AdminComment ac;
		this.sql = """
				SELECT
					comment_id,
					sc.post_id,
					post_title,
					to_char(created_date, 'YYYY-MM-DD') created_date,
					comment_text
				FROM
					SEMI_COMMENT sc
				JOIN SEMI_TOPIC st ON
					sc.POST_ID = st.POST_ID
				WHERE sc.user_id = ?
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ac = new AdminComment();

				ac.setCommentId(rs.getInt("comment_id"));
				ac.setPostId(rs.getInt("post_id"));
				ac.setPostTitle(rs.getString("post_title"));
				ac.setCreatedDate(rs.getString("created_date"));
				ac.setCommentText(rs.getString("comment_text"));

				acList.add(ac);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return acList;
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
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}

	public int insertUserInactive(AdminInactive form) {
		int rowCount = 0;
		this.sql = """
				insert into semi_user_inactive
				values(?, ?, sysdate, sysdate + ?)
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, form.getUserId());
			pstmt.setString(2, form.getDesc());
			pstmt.setInt(3, form.getLength());

			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

	public int isUserInactive(int userId) {
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
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
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