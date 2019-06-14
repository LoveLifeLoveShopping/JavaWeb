package cn.com.demo.system.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.demo.system.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Log log = LogFactory.getLog(UserController.class);
	private static final String USER_LIST_PATH_NAME = "userList";
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	 @RequestMapping(method = RequestMethod.GET)
	    public String getBookList(ModelMap modelMap) {
	        log.info("获取用户信息.");
	        modelMap.addAttribute("userList", userServiceImpl.queryAllUser());
	        return USER_LIST_PATH_NAME;
	    }
	
}
