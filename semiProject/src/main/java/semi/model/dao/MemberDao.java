package semi.model.dao;

import java.sql.*;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.model.Member;
import semi.model.Member_Login;
import semi.model.Member_SignUp;

@Repository
public class MemberDao {

    @Autowired
    private DataSource ds;
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private String sql;

    public MemberDao() {
    }

    public MemberDao(DataSource ds) {
        this.ds = ds;
    }

    public Member selectOneUser(int id) {
		this.sql = "select member_id, member_email, member_pw, where user_id=" + id;
		Member member = new Member();
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				member.setMember_id(rs.getInt("member_id"));
				member.setMember_email(rs.getString("member_email"));
				member.setMember_pw(rs.getString("member_pw"));
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
		
		
		return member;
	}

//	public List<User> selectAllUser() {
//		this.sql = "select * from semi_user order by user_id";
//		List<User> userList = new ArrayList<User>();
//
//		try {
//			conn = ds.getConnection();
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//
//			while (rs.next()) {
//				User u = new User();
//
//				u.setUser_id(rs.getInt("user_id"));
//				u.setUser_name(rs.getString("user_name"));
//				u.setUser_email(rs.getString("user_email"));
//
//				userList.add(u);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (!conn.isClosed()) {
//					conn.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//
//		return userList;
//	}

    // 회원가입 처리 메소드
    public boolean registerUser(Member_SignUp signUpForm) {
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