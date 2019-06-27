package cn.com.demo.system.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.demo.system.dao.IUserDao;
import cn.com.demo.system.entity.User;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 获取所有用户
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<User> queryAllUser() {
		String sql = "SELECT id,username,password FROM user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public List<User> logonAudit(String username, String password) {
		String sql = "SELECT id,username,password FROM user WHERE username = ? and password = ? ";
		return jdbcTemplate.query(sql, new Object[] { username, password },
				new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public User updateUser(User user) {
		String sql = "UPDATE USER SET username=?,password=? WHERE id=?";
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getId());		
		return user;
	}

	@Override
	public User addUser(User user) {
		String sql = "INSERT INTO user (username,password) VALUES(?,?)";
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
		return user;
	}

	@Override
	public int deleteUser(int id) {
		String sql = "DELETE FROM user WHERE id=? ";
		 int arg0 =  jdbcTemplate.update(sql, id);
         return arg0;		 
	}

	@Override
	public User getUser(int id) {
		String sql = "select * from user where id = ?";
		List<User> userList = this.jdbcTemplate.query(sql, new UserRowMapper(), id);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		} else {
			return null;
		}

	}

	private class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int arg0) throws SQLException {
			final User user = new User();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setId(rs.getLong("id"));
			return user;
		}

	}

	/**
	 * 
	 * 用户分页查询
	 */
	@Override
	public List<User> queryUserbyPage(int pagenum, int pagerow) {
		int starter = (pagenum-1)*pagerow;
		 String sql = "select id, username, password from user order by id desc  limit " + starter +" , "+ pagerow;
		 List<User> list = jdbcTemplate.query(sql,new UserRowMapper());
		return list;
	}

	@Override
	public int countAllUser() {
		String sql = "select  count(1)  from user  " ;
		int countAllUaer = jdbcTemplate.queryForObject(sql, Integer.class);
		return countAllUaer;
	}

}
