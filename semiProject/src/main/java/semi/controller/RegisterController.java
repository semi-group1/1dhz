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

	@RequestMapping("register/registerPage")
	public String handleregister() {
		return "register/registerPage";
	}

	// RegisterProcess
	@RequestMapping("register/RegisterProcess")
	public String RegisterProcess(){
		return "redirect:/register/registerPage";
		
	}

}
