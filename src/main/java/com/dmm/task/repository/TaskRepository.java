package com.dmm.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmm.task.entity.Tasks;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, String> {
    List<Tasks> findByUser(String user);
}