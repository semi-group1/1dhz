package semi.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import semi.model.dao.MemberDao;

@Controller
public class RegisterController {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "scott";
	private static final String pwd = "tiger";
	private Connection con;
	private PreparedStatement pstmt;

	@RequestMapping("register/registerPage")
	public String handleregister() {
		return "register/registerPage";
	}

	// RegisterProcess
	@RequestMapping("register/RegisterProcess")
	public String RegisterProcess(HttpServletRequest request) throws UnsupportedEncodingException, SQLException {
		request.setCharacterEncoding("utf-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
//		connDB();
		
		// 
		String sql = "insert into T_MEMBER(email,pw) values(?,?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		pstmt.executeUpdate();
		pstmt.close();

		return "redirect:/register/registerPage";
	}

//	private void connDB() {
//		try {
//			Class.forName(driver);
//			System.out.println("Oracle 드라이버 로딩 성공");
//			con = DriverManager.getConnection(url, user, pwd);
//			System.out.println("Connection 생성 성공");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
