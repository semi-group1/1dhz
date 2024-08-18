package semi.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

import semi.model.*;

public class AdminReportDAO {
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

	public List<AdminReport> selectReportsByType(AdminReportType adminReportType) {
		List<AdminReport> adminReports = new ArrayList<AdminReport>();
		this.sql = """
				SELECT  report_id, report_type, report_target_id, report_user_id, user_name, report_desc, to_char(reported_date, 'YYYY-MM-DD') reported_date
				FROM SEMI_REPORT sr JOIN SEMI_USER su ON su.USER_ID = sr.REPORT_USER_ID
				WHERE REPORT_TYPE = ?
				""";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminReportType.toString());
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