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

    public Map<LocalDate, List<Tasks>> getTasks(LocalDate startDate, LocalDate endDate) {
        List<Tasks> tasksList = taskRepository.findByDateBetween(
                startDate.atStartOfDay().toLocalDate(),
                endDate.atTime(23, 59, 59).toLocalDate(),
                "name"
        );

        return tasksList.stream()
                .collect(Collectors.groupingBy(Tasks::getDate, Collectors.toList()));
    }

    // タスクをデータベースに保存するメソッド
    public void saveTask(Tasks task) {

        taskRepository.save(task);
        
    }
}