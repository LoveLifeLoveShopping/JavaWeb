package cn.com.demo.system.entity;

import java.io.Serializable;




public class User implements Serializable {

	private static final long serialVersionUID = 5912001281592127293L;

	private Long id;
	private String username;
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
	
		return this.id+this.username+this.password;
	}
}
