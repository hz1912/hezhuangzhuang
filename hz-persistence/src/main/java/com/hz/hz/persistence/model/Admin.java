package com.hz.hz.persistence.model;

import java.io.Serializable;

public class Admin implements Serializable {
	private static final long serialVersionUID = 0X631E9C1DC3CF9CF7L;
	private Integer id; // type in db: bigint(20)	Character: 	Comment: admin表
	private String name; // type in db: varchar(255)	Character: utf8mb4	Comment: 姓名
	private String password; // type in db: varchar(255)	Character: utf8mb4	Comment: 密码
	private String salt; // type in db: varchar(255)	Character: utf8mb4	Comment: 盐密码
	private java.util.Date createTime; // type in db: datetime	Character: 	Comment: 创建时间
	private java.util.Date updateTime; // type in db: datetime	Character: 	Comment: 修改时间

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return this.salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}


	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password + ", salt=" + salt + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
}
