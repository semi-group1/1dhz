package semi.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import semi.model.Member_Login;

public class MainDao {
    private DataSource ds;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private String sql;

    public MainDao() {
    }

    public MainDao(DataSource ds) {
        this.ds = ds;
    }

    /** 사용자 로그인 체크 */
    public Member_Login checkUser(String username, String password) {
        Member_Login user = null;
        sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return user;
    }

    /** 자원 정리 메소드 */
    private void closeResources() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
