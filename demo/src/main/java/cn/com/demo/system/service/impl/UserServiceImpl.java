package cn.com.demo.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.demo.system.dao.IUserDao;
import cn.com.demo.system.entity.User;
import cn.com.demo.system.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao iUserDao;

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	public List<User> queryAllUser() {
		return iUserDao.queryAllUser();
	}

	/**
	 * 用户登录验证
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public List<User> logonAudit(String username, String password) {
		return iUserDao.logonAudit(username, password);
	}

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return iUserDao.updateUser(user);
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public User addUser(User user) {
		return iUserDao.addUser(user);
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public int deleteUser(int id) {
		int arg0 = iUserDao.deleteUser(id);
		return arg0;
	}

	/**
	 * 获取用户
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public User getUser(int id) {
		return iUserDao.getUser(id);
	}

	/**
	 * 分页查询用户
	 * 
	 * @param pagenum
	 * @param pagerow
	 * @return
	 */
	@Override
	public List<User> queryUserbyPage(int pagenum, int pagerow) {
		return iUserDao.queryUserbyPage(pagenum, pagerow);
	}

	/**
	 * 统计用户数量
	 * 
	 * @return
	 */
	@Override
	public int countAllUser() {
		return iUserDao.countAllUser();
	}

}
