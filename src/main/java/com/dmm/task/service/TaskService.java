package com.dmm.task.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dmm.task.model.entity.Tasks;
import com.dmm.task.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

	public Map<LocalDate, List<Tasks>> getTasks() {
		
		// 現在の月の初日のLocalDateを取得する
		LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
		// 月の最終日を取得
		LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
		
		// 月の1日から最終日までのタスクを取得
		List<Tasks> tasksList = taskRepository.findByDateBetween(
				firstDayOfMonth.atStartOfDay().toLocalDate(),
				lastDayOfMonth.atTime(23, 59, 59).toLocalDate(),
				"name"
				);
		
		return tasksList.stream()
				.collect(Collectors.groupingBy(Tasks::getDate, Collectors.toList()));
	}

}