package cn.com.demo.system.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.demo.system.entity.User;
import cn.com.demo.system.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/")
public class UserController {



	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String queryAllUser(ModelMap modelMap) {
		modelMap.addAttribute("userList", userServiceImpl.queryAllUser());
		return "userList";
	}

	@GetMapping("/login")
	public String login(ModelMap modelMap) {		   
		return "login";
	}
	
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, Model model) {
    	
    	List<User> user = userServiceImpl.logonAudit(username, password);    	
        if (user !=null && user.size()==1) {
            session.setAttribute("username", username);
            return "redirect:/user";
        }
        model.addAttribute("errors", "用户名或密码不正确");
        return "login";
    }
    
    
    
	@GetMapping("/homepage")
	public String homepage(ModelMap modelMap) {		   
		return "homepage";
	}
    

}
