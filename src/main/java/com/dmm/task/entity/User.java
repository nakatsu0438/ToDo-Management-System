package com.dmm.task.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "password") // 自動生成されるtoStringにpasswordを出力しない
public class User {
	@Id
	public String userName;
	public String password;
	public String name;
	public String roleName;
	public String email;
}