package cn.com.demo.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.demo.system.dao.IUserDao;
import cn.com.demo.system.entity.User;
import cn.com.demo.system.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{


	
	@Autowired
	private IUserDao iUserDao;
	

	/**
	 * 获取所有用户
	 */
	public List<User> queryAllUser() {
		return iUserDao.queryAllUser();
	}


	@Override
	public List<User> logonAudit(String username, String password) {		
		return iUserDao.logonAudit(username, password);
	}


	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return iUserDao.updateUser(user);
	}


	@Override
	public User addUser(User user) {		
		return iUserDao.addUser(user);
	}


	@Override
	public int deleteUser(int id) {
		int arg0 = iUserDao.deleteUser(id);	
		return arg0;
	}


	@Override
	public User getUser(int id) {	
		return iUserDao.getUser(id);
	}
	

}
