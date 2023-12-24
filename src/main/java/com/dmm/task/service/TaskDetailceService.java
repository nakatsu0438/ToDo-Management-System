package com.dmm.task.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.dmm.task.entity.Task;
import com.dmm.task.repository.TaskRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskDetailceService implements TaskDetails {

 @Autowired
 private TaskRepository taskRepository;

 public List<List<LocalDate>> generateCalendarMatrix(LocalDate date, String username) {


     return generateCalendarMatrix(date, username);
 }

@Override
public List<Task> getUserTasks(LocalDate date, String username) {
	// TODO 自動生成されたメソッド・スタブ
	return null;
}

}