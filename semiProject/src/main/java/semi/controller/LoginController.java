package semi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

import semi.model.dao.MemberDao;
import java.sql.Connection;
import java.sql.DriverManager;

@Controller
public class LoginController {
   //로그인페이지 이동
   @RequestMapping("login/loginPage")
   public String handlelogin() {
      //connDB();
      return "login/loginPage";
   }
   
   @GetMapping("/main/mainPage")
   public String mainPage() {
       return "main/mainPage";  // mainPage.jsp 또는 mainPage.html로 이동
   }
   
}


