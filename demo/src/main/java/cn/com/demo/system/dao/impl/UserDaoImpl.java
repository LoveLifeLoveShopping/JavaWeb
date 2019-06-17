package cn.com.demo.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.demo.system.dao.IUserDao;
import cn.com.demo.system.entity.User;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional(readOnly = true)
	public List<User> queryAllUser() {
		String sql = "SELECT id,username,password FROM user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public List<User> logonAudit(String username, String password) {
		String sql = "SELECT id,username,password FROM user WHERE username = ? and password = ? ";
		return  jdbcTemplate.query(sql,  new Object[]{username,password},new BeanPropertyRowMapper<User>(User.class));
	}

}
