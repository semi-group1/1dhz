package semi.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.model.*;

@Repository
public class AdminTopicDAO {

	@Autowired
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

	public void setPaging() {
		this.sql = """
				select *
				from (select rownum as rn, t.* from (
				""" + this.sql + """
				) t)
				where rn between ? and ?
				""";
	}

	public List<AdminTopic> selectAllTopics() {
		List<AdminTopic> atList = new ArrayList<AdminTopic>();
		this.sql = """
				SELECT post_id, post_title, to_char(post_date, 'YYYY-MM-DD') post_date, user_name, post_status
				FROM SEMI_TOPIC st
				JOIN SEMI_USER su ON st.USER_ID = su.USER_ID
				""";

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				AdminTopic at = new AdminTopic();

				at.setPostId(rs.getInt("post_id"));
				at.setPostTitle(rs.getString("post_title"));
				at.setPostCreatedDate(rs.getString("post_date"));
				at.setPostUserName(rs.getString("user_name"));
				at.setPostStatus(rs.getString("post_status"));

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atList;
	}

	public int countAllTopic() {
		int rowNum = 0;
		this.sql = """
				select count(*) as count
				from semi_topic
				""";
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				rowNum = rs.getInt("count");
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
		return rowNum;
	}

	public List<AdminTopic> selectAllTopics(int startNum, int endNum) {
		List<AdminTopic> atList = new ArrayList<AdminTopic>();
		this.sql = """
				SELECT post_id, post_title, to_char(post_date, 'YYYY-MM-DD') post_date, user_name, post_status
				FROM SEMI_TOPIC st
				JOIN SEMI_USER su ON st.USER_ID = su.USER_ID
				""";
		this.setPaging();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminTopic at = new AdminTopic();

				at.setPostId(rs.getInt("post_id"));
				at.setPostTitle(rs.getString("post_title"));
				at.setPostCreatedDate(rs.getString("post_date"));
				at.setPostUserName(rs.getString("user_name"));
				at.setPostStatus(rs.getString("post_status"));

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atList;
	}

	public List<AdminCategory> selectGeneralCategories() {
		List<AdminCategory> acList = new ArrayList<AdminCategory>();
		this.sql = """
				SELECT
					TOPIC_CATEGORY_ID,
					topic_category_name
				FROM
					SEMI_TOPIC_CATEGORY stc
				ORDER BY TOPIC_CATEGORY_ID
				""";
		AdminCategory ac = null;

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ac = new AdminCategory();

				ac.setCategoryId(rs.getInt("topic_category_id"));
				ac.setCategoryName(rs.getString("topic_category_name"));

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return acList;
	}

	public List<AdminTopic> selectGeneralTopics() {
		List<AdminTopic> atList = new ArrayList<AdminTopic>();
		this.sql = """
				SELECT post_id, topic_category_name, post_title, to_char(post_date, 'YYYY-MM-DD') post_date, user_name, post_status
				FROM SEMI_TOPIC st
				JOIN SEMI_TOPIC_CATEGORY stc ON st.CATE_NO = stc.TOPIC_CATEGORY_ID
				JOIN SEMI_USER su ON st.USER_ID = su.USER_ID
				WHERE job_yn = 'n'
				""";

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				AdminTopic at = new AdminTopic();

				at.setPostId(rs.getInt("post_id"));
				at.setPostCategoryName(rs.getString("topic_category_name"));
				at.setPostTitle(rs.getString("post_title"));
				at.setPostCreatedDate(rs.getString("post_date"));
				at.setPostUserName(rs.getString("user_name"));
				at.setPostStatus(rs.getString("post_status"));

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atList;
	}

	public int countGeneralTopic() {
		int rowNum = 0;
		this.sql = """
				select count(*) as count from semi_topic where job_yn = 'n'
				""";
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				rowNum = rs.getInt("count");
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

		return rowNum;
	}

	public List<AdminTopic> selectGeneralTopics(int startNum, int endNum) {
		List<AdminTopic> atList = new ArrayList<AdminTopic>();
		this.sql = """
				SELECT post_id, topic_category_name, post_title, to_char(post_date, 'YYYY-MM-DD') post_date, user_name, post_status
				FROM SEMI_TOPIC st
				JOIN SEMI_TOPIC_CATEGORY stc ON st.CATE_NO = stc.TOPIC_CATEGORY_ID
				JOIN SEMI_USER su ON st.USER_ID = su.USER_ID
				WHERE job_yn = 'n'
				order by post_id
				""";
		this.setPaging();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminTopic at = new AdminTopic();

				at.setPostId(rs.getInt("post_id"));
				at.setPostCategoryName(rs.getString("topic_category_name"));
				at.setPostTitle(rs.getString("post_title"));
				at.setPostCreatedDate(rs.getString("post_date"));
				at.setPostUserName(rs.getString("user_name"));
				at.setPostStatus(rs.getString("post_status"));

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atList;
	}

	public int countByCategoryId(int categoryId) {
		int rowNum = 0;
		this.sql = """
				select count(*) as count
				FROM SEMI_TOPIC st
				JOIN SEMI_TOPIC_CATEGORY stc ON st.CATE_NO = stc.TOPIC_CATEGORY_ID
				WHERE job_yn = 'n' and topic_category_id = ?
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				rowNum = rs.getInt("count");
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

		return rowNum;
	}

	public List<AdminTopic> selectByCategoryId(int categoryId, int startNum, int endNum) {
		List<AdminTopic> atList = new ArrayList<AdminTopic>();
		this.sql = """
				SELECT post_id, topic_category_name, post_title, to_char(post_date, 'YYYY-MM-DD') post_date, user_name, post_status
				FROM SEMI_TOPIC st
				JOIN SEMI_TOPIC_CATEGORY stc ON st.CATE_NO = stc.TOPIC_CATEGORY_ID
				JOIN SEMI_USER su ON st.USER_ID = su.USER_ID
				WHERE job_yn = 'n' and topic_category_id = ?
				ORDER BY post_id
				""";

		this.setPaging();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminTopic at = new AdminTopic();

				at.setPostId(rs.getInt("post_id"));
				at.setPostCategoryName(rs.getString("topic_category_name"));
				at.setPostTitle(rs.getString("post_title"));
				at.setPostCreatedDate(rs.getString("post_date"));
				at.setPostUserName(rs.getString("user_name"));
				at.setPostStatus(rs.getString("post_status"));

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atList;
	}

	public List<AdminTopic> selectByCategoryId(int categoryId) {
		List<AdminTopic> atList = new ArrayList<AdminTopic>();
		this.sql = """
				SELECT post_id, topic_category_name, post_title, to_char(post_date, 'YYYY-MM-DD') post_date, user_name, post_status
				FROM SEMI_TOPIC st
				JOIN SEMI_TOPIC_CATEGORY stc ON st.CATE_NO = stc.TOPIC_CATEGORY_ID
				JOIN SEMI_USER su ON st.USER_ID = su.USER_ID
				WHERE job_yn = 'n' and topic_category_id = ?
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminTopic at = new AdminTopic();

				at.setPostId(rs.getInt("post_id"));
				at.setPostCategoryName(rs.getString("topic_category_name"));
				at.setPostTitle(rs.getString("post_title"));
				at.setPostCreatedDate(rs.getString("post_date"));
				at.setPostUserName(rs.getString("user_name"));
				at.setPostStatus(rs.getString("post_status"));

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atList;
	}

	public int countByJobCategoryId(int categoryId) {
		int rowNum = 0;
		this.sql = """
				select count(*) as count from semi_topic where job_yn = 'y' and cate_no = ?
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				rowNum = rs.getInt("count");
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

		return rowNum;
	}

	public List<AdminTopic> selectByJobCategoryId(int categoryId, int startNum, int endNum) {
		List<AdminTopic> atList = new ArrayList<AdminTopic>();
		this.sql = """
				SELECT post_id, job_category_super, job_category_sub, post_title, to_char(post_date, 'YYYY-MM-DD') post_date, user_name, post_status
				FROM SEMI_TOPIC st
				JOIN SEMI_JOB_CATEGORY sjc ON st.CATE_NO = sjc.JOB_CATEGORY_ID
				JOIN SEMI_USER su ON st.USER_ID = su.USER_ID
				WHERE job_yn = 'y' and job_category_id = ?
				order by post_id
				""";
		this.setPaging();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminTopic at = new AdminTopic();

				at.setPostId(rs.getInt("post_id"));
				at.setPostCategoryName(rs.getString("job_category_super") + " > " + rs.getString("job_category_sub"));
				at.setPostTitle(rs.getString("post_title"));
				at.setPostCreatedDate(rs.getString("post_date"));
				at.setPostUserName(rs.getString("user_name"));
				at.setPostStatus(rs.getString("post_status"));

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atList;
	}

	public List<AdminTopic> selectByJobCategoryId(int categoryId) {
		List<AdminTopic> atList = new ArrayList<AdminTopic>();
		this.sql = """
				SELECT post_id, job_category_super, job_category_sub, post_title, to_char(post_date, 'YYYY-MM-DD') post_date, user_name, post_status
				FROM SEMI_TOPIC st
				JOIN SEMI_JOB_CATEGORY sjc ON st.CATE_NO = sjc.JOB_CATEGORY_ID
				JOIN SEMI_USER su ON st.USER_ID = su.USER_ID
				WHERE job_yn = 'y' and job_category_id = ?
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminTopic at = new AdminTopic();

				at.setPostId(rs.getInt("post_id"));
				at.setPostCategoryName(rs.getString("job_category_super") + " > " + rs.getString("job_category_sub"));
				at.setPostTitle(rs.getString("post_title"));
				at.setPostCreatedDate(rs.getString("post_date"));
				at.setPostUserName(rs.getString("user_name"));
				at.setPostStatus(rs.getString("post_status"));

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atList;
	}

	public int countJobTopics() {
		int rowNum = 0;
		this.sql = """
				select count(*) as count from semi_topic where job_yn = 'y'
				""";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				rowNum = rs.getInt("count");
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

		return rowNum;
	}

	public List<AdminTopic> selectJobTopics(int startNum, int endNum) {
		List<AdminTopic> atList = new ArrayList<AdminTopic>();
		this.sql = """
				SELECT post_id, job_category_super, job_category_sub, post_title, to_char(post_date, 'YYYY-MM-DD') post_date, user_name, post_status
				FROM SEMI_TOPIC st
				JOIN SEMI_JOB_CATEGORY sjc ON st.CATE_NO = sjc.JOB_CATEGORY_ID
				JOIN SEMI_USER su ON st.USER_ID = su.USER_ID
				WHERE job_yn = 'y'
				""";
		this.setPaging();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminTopic at = new AdminTopic();

				at.setPostId(rs.getInt("post_id"));
				at.setPostCategoryName(rs.getString("job_category_super") + " > " + rs.getString("job_category_sub"));
				at.setPostTitle(rs.getString("post_title"));
				at.setPostCreatedDate(rs.getString("post_date"));
				at.setPostUserName(rs.getString("user_name"));
				at.setPostStatus(rs.getString("post_status"));

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atList;
	}

	public List<AdminCategory> selectJobCategories() {
		List<AdminCategory> acList = new ArrayList<AdminCategory>();
		this.sql = """
				SELECT
					job_category_id,
					job_category_super
				FROM
					(
					SELECT
						job_category_id,
						job_category_super,
						ROW_NUMBER() OVER (PARTITION BY job_category_super
					ORDER BY
						JOB_CATEGORY_ID) AS rn
					FROM
						SEMI_JOB_CATEGORY sjc
					ORDER BY
						JOB_CATEGORY_ID
						)
				WHERE rn = 1
				""";
		AdminCategory ac = null;

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ac = new AdminCategory();

				ac.setCategoryId(rs.getInt("job_category_id"));
				ac.setCategoryName(rs.getString("job_category_super"));

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return acList;
	}

	public List<AdminTopic> selectJobTopics() {
		List<AdminTopic> atList = new ArrayList<AdminTopic>();
		this.sql = """
				SELECT post_id, job_category_super, job_category_sub, post_title, to_char(post_date, 'YYYY-MM-DD') post_date, user_name, post_status
				FROM SEMI_TOPIC st
				JOIN SEMI_JOB_CATEGORY sjc ON st.CATE_NO = sjc.JOB_CATEGORY_ID
				JOIN SEMI_USER su ON st.USER_ID = su.USER_ID
				WHERE job_yn = 'y'
				""";

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				AdminTopic at = new AdminTopic();

				at.setPostId(rs.getInt("post_id"));
				at.setPostCategoryName(rs.getString("job_category_super") + " > " + rs.getString("job_category_sub"));
				at.setPostTitle(rs.getString("post_title"));
				at.setPostCreatedDate(rs.getString("post_date"));
				at.setPostUserName(rs.getString("user_name"));
				at.setPostStatus(rs.getString("post_status"));

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atList;
	}

	public int updatePostStatus(int postId, AdminPostStatus status) {
		int rowCount = 0;
		this.sql = """
				update semi_topic
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

	public int insertPostInactive(AdminInactive form) {
		int rowCount = 0;
		this.sql = """
				insert into semi_post_inactive
				values(?, ?, ?, sysdate)
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, form.getPostId());
			pstmt.setString(2, form.getType());
			pstmt.setString(3, form.getDesc());

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

	public int isPostInactive(int postId) {
		int count = 0;
		this.sql = """
				SELECT COUNT(*) AS post_count
				FROM SEMI_POST_INACTIVE spi
				WHERE spi.INACTIVE_POST_ID =?
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
