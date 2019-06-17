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
	

	public List<User> queryAllUser() {
		return iUserDao.queryAllUser();
	}


	@Override
	public List<User> logonAudit(String username, String password) {		
		return iUserDao.logonAudit(username, password);
	}
	

}
