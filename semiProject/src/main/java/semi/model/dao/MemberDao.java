package semi.model.dao;

import java.sql.*;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.model.Member;
import semi.model.Member_Login;
import semi.model.Member_Register;

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

    public Member selectOneUser(int id, String user_email, String user_password) {
		this.sql = "select member_id, member_email, member_pw, where user_id=" + id;
		Member member = new Member();
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs.next()) {
				member.setUser_id(rs.getInt("user_id"));
				member.setUser_email(rs.getString("user_email"));
				member.setUser_pw(rs.getString("user_pw"));
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
    
	public boolean registerUser(Member_Register signUpForm) {
		Member member = new Member();
		int rowCount = 0;
		this.sql = """
				        		insert into semi_user(user_id, user_name, user_email, user_pw, user_job)
				values(seq_semi_user_id.nextval, ?, ?, ? ,1)
				        		""";

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, signUpForm.getUser_name());
			pstmt.setString(2, signUpForm.getUser_email());
			pstmt.setString(3, signUpForm.getUser_pw());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
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

		return rowCount > 0;
	}
	
	public Member findByEmail(String email) {
        Member member = null;
        this.sql = "SELECT member_id, member_email, member_pw FROM semi_user WHERE member_email = ?";
        
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = new Member();
                member.setUser_id(rs.getInt("member_id"));
                member.setUser_email(rs.getString("member_email"));
                member.setUser_pw(rs.getString("member_pw"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        
        return member;
    }
//    
//    public Member selectOneUser(int id) {
//		this.sql = "select member_id, member_email, member_pw, where user_id=" + id;
//		Member member = new Member();
//		try {
//			conn = ds.getConnection();
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//
//			while (rs.next()) {
//				member.setUser_id(rs.getInt("user_id"));
//				member.setUser_email(rs.getString("user_email"));
//				member.setUser_pw(rs.getString("user_pw"));
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
//		
//		return member;
//	}
//    
//    insert into T_MEMBER(email,pw) values(?,?)
    
    

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
    
}