package com.dmm.task.service;

import java.time.LocalDate;
import java.util.List;

import com.dmm.task.entity.Task;

public interface TaskDetails {
	
    List<List<LocalDate>> generateCalendarMatrix(LocalDate date, String username);

    List<Task> getUserTasks(LocalDate date, String username);

}