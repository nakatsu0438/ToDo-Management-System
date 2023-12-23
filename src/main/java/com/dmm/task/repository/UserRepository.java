package com.dmm.task.repository;

import java.time.LocalDateTime;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dmm.task.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
}