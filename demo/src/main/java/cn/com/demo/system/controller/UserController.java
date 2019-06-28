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
			// 获取分页数据第一页 每页6条数据
			modelMap.addAttribute("homepage", userServiceImpl.queryUserbyPage(1, 6));
			// 获取总数据数量
			int totalNum = userServiceImpl.countAllUser();
			// 总页数
			int totalPage = 0;
			if (totalNum % totalNum == 0) {
				totalPage = totalNum / 6;
			} else {
				totalPage = totalNum / 6 + 1;
			}
			// 默认显示第一页数据
			modelMap.addAttribute("currentPage", 1);
			modelMap.addAttribute("totalPage", totalPage);
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
		modelMap.addAttribute("homepage", userServiceImpl.queryUserbyPage(1, 6));
		int totalNum = userServiceImpl.countAllUser();
		int totalPage = 0;
		if (totalNum % totalNum == 0) {
			totalPage = totalNum / 6;
		} else {
			totalPage = totalNum / 6 + 1;
		}
		modelMap.addAttribute("currentPage", 1);
		modelMap.addAttribute("totalPage", totalPage);
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
		List<User> list = userServiceImpl.queryUserbyPage(1, 6);
		modelMap.addAttribute("homepage", list);
		int totalNum = userServiceImpl.countAllUser();
		int totalPage = 0;
		if (totalNum % 6 == 0) {
			totalPage = totalNum / 6;
		} else {
			totalPage = totalNum / 6 + 1;
		}
		modelMap.addAttribute("currentPage", 1);
		modelMap.addAttribute("totalPage", totalPage);
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
	 * 
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteUser")
	public String deleteUser(ModelMap modelMap, int id, HttpSession session) {
		int arg0 = userServiceImpl.deleteUser(id);
		session.setAttribute("deleteUserArg0", arg0);
		modelMap.addAttribute("homepage", userServiceImpl.queryUserbyPage(1, 6));
		int totalNum = userServiceImpl.countAllUser();
		int totalPage = 0;
		if (totalNum % 6 == 0) {
			totalPage = totalNum / 6;
		} else {
			totalPage = totalNum / 6 + 1;
		}
		modelMap.addAttribute("currentPage", 1);
		modelMap.addAttribute("totalPage", totalPage);
		return "homepage";
	}

	/**
	 * 分页查询用户
	 * 
	 * @param currentPage 当前页
	 * @param linage      每页数量
	 * @return
	 */
	@RequestMapping("/queryUserByPage")
	public String queryUserByPage(ModelMap modelMap, Integer currentPage, Integer linage) {

		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		if (linage == null || linage == 0) {
			linage = 6;
		}
		List<User> list = userServiceImpl.queryUserbyPage(currentPage, linage);
		int totalNum = userServiceImpl.countAllUser();
		int totalPage = 0;
		if (totalNum % linage == 0) {
			totalPage = totalNum / linage;
		} else {
			totalPage = totalNum / linage + 1;
		}

		System.out.println("目前分页的总页数是" + totalPage);
		System.out.println("当前页是" + currentPage);
		System.out.println("总数据是" + totalNum);

		modelMap.addAttribute("homepage", list);
		modelMap.addAttribute("currentPage", currentPage);
		modelMap.addAttribute("totalPage", totalPage);
		return "homepage";

	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("username", null);
		return "login";
	}

}
