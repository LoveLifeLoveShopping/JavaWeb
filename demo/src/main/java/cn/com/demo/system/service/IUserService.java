package cn.com.demo.system.service;

import java.util.List;

import cn.com.demo.system.entity.User;

public interface IUserService {

	List<User>queryAllUser();
	
	List<User> logonAudit(String username,String password);
	
	User updateUser(User user);
	
	User addUser(User user);
	
	void deleteUser(int id);
	
	User getUser(int id);
}
