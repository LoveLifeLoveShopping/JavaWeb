package cn.com.demo.system.dao;

import java.util.List;

import cn.com.demo.system.entity.User;

public interface IUserDao {

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	List<User> queryAllUser();

	/**
	 * 用户登录验证
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	List<User> logonAudit(String username, String password);

	/**
	 * 用户更新
	 * 
	 * @param user
	 * @return
	 */
	User updateUser(User user);

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	User addUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	int deleteUser(int id);

	/**
	 * 获取用户
	 * 
	 * @param id
	 * @return
	 */
	User getUser(int id);

	/**
	 * 分页查询用户
	 * 
	 * @param pagenum
	 * @param pagerow
	 * @return
	 */
	List<User> queryUserbyPage(int pagenum, int pagerow);

	/**
	 * 统计用户总数量
	 * 
	 * @return
	 */
	int countAllUser();

}
