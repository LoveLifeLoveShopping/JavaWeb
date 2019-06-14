package cn.com.demo.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.com.demo.system.entity.User;

@Service
public class UserServiceImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<User> queryAllUser() {
		String sql = "SELECT id,username,password FROM user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}

	

}
