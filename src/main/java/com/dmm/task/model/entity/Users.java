package com.dmm.task.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "password")
public class Users implements Serializable {
	@Id
	public String userName;
	public String password;
	public String name;
	public String roleName;
}