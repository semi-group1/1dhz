package semi.model.dao;

import java.sql.*;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import semi.model.LoginForm;
import semi.model.SignUpForm;

@Repository
public class MemberDao {

    @Autowired
    private DataSource ds;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private String sql;

    public MemberDao() {
    }

    public MemberDao(DataSource ds) {
        this.ds = ds;
    }

    // 로그인 검증 메소드
    public boolean validateUser(String userEmail, String userPassword) {
        this.sql = """
                SELECT COUNT(*) AS user_count
                FROM semi_user
                WHERE user_email = ? AND user_pw = ?
                """;

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userEmail);
            pstmt.setString(2, userPassword);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_count") > 0; // 로그인 성공 여부 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false; // 기본적으로 실패로 간주
    }

    // 회원가입 처리 메소드
    public boolean registerUser(SignUpForm signUpForm) {
        this.sql = "INSERT INTO semi_user (user_email, user_pw) VALUES (?, ?)";

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, signUpForm.getUser_email());
            pstmt.setString(2, signUpForm.getUser_pw());

            int rowCount = pstmt.executeUpdate();
            return rowCount > 0; // 성공 여부 반환
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false; // 기본적으로 실패로 간주
    }
}