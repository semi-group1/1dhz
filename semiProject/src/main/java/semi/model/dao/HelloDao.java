package semi.model.dao;

import java.sql.*;
import java.util.*;

import org.apache.tomcat.jdbc.pool.DataSource;

import semi.model.Employee;

public class HelloDao {
	private DataSource ds;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	public HelloDao() {
	}

	public HelloDao(DataSource ds) {
		this.ds = ds;
	}

	public List<Employee> selectAll() {
		this.sql = "select * from emp order by empno";
		List<Employee> emp = new ArrayList<Employee>();

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Employee e = new Employee();

				e.setEmpno(rs.getInt("empno"));
				e.setEname(rs.getString("ename"));
				// 기타 생략

				emp.add(e);
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

		return emp;
	}
}
