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