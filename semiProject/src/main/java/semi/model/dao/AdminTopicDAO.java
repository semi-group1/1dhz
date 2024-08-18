package semi.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import org.apache.tomcat.jdbc.pool.DataSource;

import semi.model.*;

public class AdminTopicDAO {
	private DataSource ds;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	public AdminTopicDAO() {
	}

	public AdminTopicDAO(DataSource ds) {
		this.ds = ds;
	}

	public List<AdminTopic> selectAllTopics() {
		List<AdminTopic> adminTopics = new ArrayList<AdminTopic>();
		this.sql = """
				SELECT post_id, topic_category_id, topic_category_name, post_title, post_date, user_name, post_status
				FROM SEMI_TOPIC st
				JOIN SEMI_TOPIC_CATEGORY stc ON st.CATE_NO = stc.TOPIC_CATEGORY_ID
				JOIN SEMI_USER su ON st.USER_ID = su.USER_ID
				""";

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

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

		return adminTopics;
	}

	public int updatePostStatus(int postId, AdminPostStatus status) {
		int rowCount = 0;
		this.sql = """
				update semi_post
				set post_status = ?
				where post_id = ?
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status.toString());
			pstmt.setInt(2, postId);
			rowCount = pstmt.executeUpdate();
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

	public int insertPostInactive(int postId, String desc) {
		int rowCount = 0;
		this.sql = """
				insert into semi_post_inactive
				values(?, ?, sysdate)
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			pstmt.setString(2, desc);
			rowCount = pstmt.executeUpdate();
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

	public int isPostInactive(int postId) {
		int count = 0;
		this.sql = """
				SELECT COUNT(*) AS post_count
				FROM SEMI_POST_INACTIVE spi
				WHERE spi.INACTIVE_POST_ID =?;
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("post_count");
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
