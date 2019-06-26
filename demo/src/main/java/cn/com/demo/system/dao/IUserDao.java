package cn.com.demo.system.dao;

import java.util.List;



import cn.com.demo.system.entity.User;


public interface IUserDao {

	List<User>queryAllUser();
	
	List<User> logonAudit(String username,String password);
	
	User updateUser(User user);
	
	User addUser(User user);
	
	int deleteUser(int id);
	
	User getUser(int id);
	/**
	 * 分页查询用户
	 * @param pagenum
	 * @param pagerow
	 * @return
	 */
	List<User>queryUserbyPage(int pagenum, int pagerow);
	/**
	 * 统计用户总数量
	 * @return
	 */
	int countAllUser();
	
}
