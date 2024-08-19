package semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import semi.model.LoginForm;
import semi.model.SignUpForm;
import semi.model.dao.MemberDao;

@Controller
public class MemberController {

    @Autowired
    MemberDao memberDao;

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @RequestMapping("/signUpPage")
    public String signUpPage() {
        return "signUpPage";
    }

//    @PostMapping("/loginProcess")
//    public String loginProcess(@ModelAttribute LoginForm loginForm, HttpServletRequest request, Model model) {
//        LoginForm isValidUser = memberDao.validateUser(loginForm.getUser_email(), loginForm.getUser_pw());
//
//        if (isValidUser) {
//            HttpSession session = request.getSession();
//            session.setAttribute("loginStatus", "success");
//            return "redirect:/mainPage";
//        } else {
//            model.addAttribute("errorMessage", "Invalid username or password.");
//            return "loginPage";
//        }
//    }
//
//    @PostMapping("/signUpProcess")
//    public String signUpProcess(@ModelAttribute SignUpForm signUpForm, Model model) {
//        boolean isSignUpSuccess = memberDao.registerUser(signUpForm);
//
//       
//    }
}
