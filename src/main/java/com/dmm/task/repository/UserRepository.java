package com.dmm.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dmm.task.model.entity.Users;

public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUserNameAndPassword(String userName, String password);

// Optional<Users> findByUserNameAndPassowrdAndActive(String userName, String password, boolean active);

    @Query("SELECT u FROM Users u WHERE u.userName = :userName")
    Optional<Users> findByUser(@Param("userName") String email);
	
}