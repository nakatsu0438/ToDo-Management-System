package com.dmm.task.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.dmm.task.entity.Tasks;
import com.dmm.task.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 他のメソッド...

    public List<Tasks> getTasksByUser(String user) {
        return taskRepository.findByUser(user);
    }

    // 他のメソッド...
}