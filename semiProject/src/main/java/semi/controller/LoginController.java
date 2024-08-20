//package semi.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import semi.model.dao.MemberDao;
//
//@Controller
//public class LoginController {
//	
//	//로그인페이지 이동
//	@RequestMapping("login/loginPage")
//	public String handlelogin() {
//		return "login/loginPage";
//	}
//}

package semi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import semi.model.dao.MemberDao;
import java.sql.Connection;
import java.sql.DriverManager;

@Controller
public class LoginController {

//   private static final String driver = "oracle.jdbc.driver.OracleDriver";
//   private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
//   private static final String user = "scott";
//   private static final String pwd = "tiger";
//   private Connection con;
   
   //로그인페이지 이동
   @RequestMapping("login/loginPage")
   public String handlelogin() {
//      connDB();
      return "login/loginPage";
   }
   
	//회원가입페이지 이동
//   @RequestMapping("/register/registerPage")
   

//   private void connDB() {
//      try {
//         Class.forName(driver);
//         System.out.println("Oracle 드라이버 로딩 성공");
//         con = DriverManager.getConnection(url, user, pwd);
//         System.out.println("Connection 생성 성공");
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//   }

}


