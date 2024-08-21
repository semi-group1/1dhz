package semi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import semi.model.dao.MainDao;

@Controller
public class MainController {

	@Autowired
	MainDao dao;

    @RequestMapping("/main")
    public String showMain() {
        return "main"; 
    }
}
