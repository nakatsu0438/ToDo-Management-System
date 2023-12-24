package com.dmm.task.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dmm.task.entity.Users;

public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUserNameAndPassword(String userName, String password);

    // または
    // Optional<Users> findByUserNameAndPassowrdAndActive(String userName, String password, boolean active);

    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Optional<Users> findByEmail(@Param("email") String email);
	
}