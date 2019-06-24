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
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	/**
	 * 登录界面
	 * 
	 * @param modelMap
	 * @return
	 */
	@GetMapping("/login")
	public String login(ModelMap modelMap) {
		return "login";
	}

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @param session
	 * @param model
	 * @param modelMap
	 * @return
	 */

	@PostMapping("/login")
	public String login(String username, String password, HttpSession session, Model model, ModelMap modelMap) {

		List<User> user = userServiceImpl.logonAudit(username, password);
		if (user != null && user.size() == 1) {
			session.setAttribute("username", username);
			modelMap.addAttribute("homepage", userServiceImpl.queryAllUser());
			return "/homepage";
		}
		model.addAttribute("errors", "用户名或密码不正确");
		return "login";
	}

	/**
	 * 登录后的界面
	 * 
	 * @return
	 */
	@GetMapping("/homepage")
	public String homepage() {
		return "homepage";
	}

	/**
	 * 
	 * 显示添加用户的界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toAddUser", method = RequestMethod.GET)
	public String addUserForm() {
		return "adduser";
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(User user, ModelMap modelMap) {
		userServiceImpl.addUser(user);
		modelMap.addAttribute("homepage", userServiceImpl.queryAllUser());
		return "homepage";
	}

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @param modelMap
	 * @return
	 */
	@PostMapping("/updateUser")
	public String updateUser(User user, ModelMap modelMap) {
		userServiceImpl.updateUser(user);
		modelMap.addAttribute("homepage", userServiceImpl.queryAllUser());
		return "homepage";
	}

	/**
	 * 
	 * 显示用户更新的界面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/toUpdateUser")
	public String toEdit(Model model, int id) {
		User user = userServiceImpl.getUser(id);
		model.addAttribute("user", user);
		return "updateuser";
	}
	
	/**
	 * 删除用户
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteUser")
	public String deleteUser(ModelMap modelMap, int id,HttpSession session) {
		int arg0 = userServiceImpl.deleteUser(id);
		session.setAttribute("deleteUserArg0", arg0);
		modelMap.addAttribute("homepage", userServiceImpl.queryAllUser());
		return "homepage";
	}

}
