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
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}

	public int insertUserInactive(int userId, String desc, int length) {
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