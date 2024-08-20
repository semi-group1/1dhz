package semi.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.model.*;

@Repository
public class AdminReportDAO {

	@Autowired
	private DataSource ds;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	public AdminReportDAO() {
	}

	public AdminReportDAO(DataSource ds) {
		this.ds = ds;
	}

	public List<AdminReport> selectPostReports() {
		List<AdminReport> arList = new ArrayList<AdminReport>();
		AdminReport ar = null;
		this.sql = """
				SELECT
					report_id, post_id, post_title, post_user_name, user_name AS report_user_name, report_desc, report_status
				FROM
					SEMI_REPORT sr
				JOIN (
					SELECT
						post_id,
						post_title,
						user_name AS post_user_name
					FROM
						SEMI_TOPIC st
					JOIN SEMI_USER su2 ON
						st.user_id = su2.USER_ID
						) st2 ON
					sr.REPORT_TARGET_ID = st2.POST_ID
				JOIN SEMI_USER su ON
					sr.REPORT_USER_ID = su.USER_ID
				WHERE sr.REPORT_TYPE = 'post'
					""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ar = new AdminReport();

				ar.setReportId(rs.getInt("report_id"));
				ar.setReportTargetId(rs.getInt("post_id"));
				ar.setReportedTitle(rs.getString("post_title"));
				ar.setReportedUsername(rs.getString("post_user_name"));
				ar.setReportUserName(rs.getString("report_user_name"));
				ar.setReportDesc(rs.getString("report_desc"));
				ar.setReportStatus(AdminReportStatus.valueOf(rs.getString("report_status")));

				arList.add(ar);
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

		return arList;
	}

	public List<AdminReport> selectUserReports() {
		List<AdminReport> arList = new ArrayList<AdminReport>();
		AdminReport ar = null;
		this.sql = """
				SELECT
					sr.report_id,
					reported_user_id,
					reported_user_name,
					user_name AS report_user_name,
					report_desc,
					report_status
				FROM
					SEMI_REPORT sr
				JOIN (
					SELECT
						report_id,
						REPORT_TARGET_ID AS reported_user_id,
						user_name AS reported_user_name
					FROM
						SEMI_REPORT sr2
					JOIN SEMI_USER su ON
						sr2.REPORT_TARGET_ID = su.USER_ID
					WHERE
						REPORT_TYPE = 'user') sru ON
					sr.REPORT_ID = sru.report_id
				JOIN SEMI_USER su2 ON
					sr.REPORT_USER_ID = su2.USER_ID
				WHERE
					sr.REPORT_TYPE = 'user'
					""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ar = new AdminReport();

				ar.setReportId(rs.getInt("report_id"));
				ar.setReportTargetId(rs.getInt("reported_user_id"));
				ar.setReportedUsername(rs.getString("reported_user_name"));
				ar.setReportUserName(rs.getString("report_user_name"));
				ar.setReportDesc(rs.getString("report_desc"));
				ar.setReportStatus(AdminReportStatus.valueOf(rs.getString("report_status")));

				arList.add(ar);
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

		return arList;
	}

	public List<AdminReport> selectCommentReports() {
		List<AdminReport> arList = new ArrayList<AdminReport>();
		AdminReport ar = null;
		this.sql = """
				SELECT
					report_id,
					comment_id,
					comment_text,
					comment_user_name,
					user_name AS report_user_name,
					report_desc,
					report_status
				FROM
					SEMI_REPORT sr
				JOIN (
					SELECT
						comment_id,
						comment_text,
						user_name AS COMMENT_USER_NAME
					FROM
						SEMI_COMMENT sc
					JOIN SEMI_USER su ON
						sc.USER_ID = su.USER_ID
				) scu ON
					sr.REPORT_TARGET_ID = scu.comment_id
				JOIN SEMI_USER su2 ON
					sr.REPORT_USER_ID = su2.USER_ID
				WHERE
					sr.REPORT_TYPE = 'comment'
					""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ar = new AdminReport();

				ar.setReportId(rs.getInt("report_id"));
				ar.setReportTargetId(rs.getInt("comment_id"));
				ar.setReportedCommentText(rs.getString("comment_text"));
				ar.setReportedUsername(rs.getString("comment_user_name"));
				ar.setReportUserName(rs.getString("report_user_name"));
				ar.setReportDesc(rs.getString("report_desc"));
				ar.setReportStatus(AdminReportStatus.valueOf(rs.getString("report_status")));

				arList.add(ar);
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

		return arList;
	}

	public List<AdminReport> selectReportsByType(AdminReportType type) {
		List<AdminReport> adminReports = new ArrayList<AdminReport>();
		this.sql = """
				SELECT  report_id, report_type, report_target_id, report_user_id, user_name, report_desc, to_char(reported_date, 'YYYY-MM-DD') reported_date
				FROM SEMI_REPORT sr JOIN SEMI_USER su ON su.USER_ID = sr.REPORT_USER_ID
				WHERE REPORT_TYPE = ?
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type.toString());
			rs = pstmt.executeQuery();
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

		return adminReports;
	}

	public int updateReportStatus(int reportId, AdminReportStatus status) {
		int rowCount = 0;
		this.sql = """
				update semi_report
				set report_status = ?
				where report_id = ?
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, status.toString());
			pstmt.setInt(2, reportId);

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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}
}