package com.dmm.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dmm.task.entity.Tasks;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, String> {
    @Query("select a from Tasks a where a.date between :from and :to and a.name = :name")
    List<Tasks> findByDateBetween(@Param("from") LocalDate from, @Param("to") LocalDate to, @Param("name") String name);
}