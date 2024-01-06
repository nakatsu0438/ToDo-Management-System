package com.dmm.task.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmm.task.entity.Tasks;
import com.dmm.task.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // 特定の日付範囲内のタスクを取得し、日付ごとにグループ化してMapに変換するメソッド
    public Map<LocalDate, List<Tasks>> getTasks(LocalDate startDate, LocalDate endDate) {

        // データベースから特定の日付範囲内のタスクを取得
        List<Tasks> tasksList = taskRepository.findByDateBetween(
                startDate.atStartOfDay().toLocalDate(),  // startDateの00:00時点
                endDate.atTime(23, 59, 59).toLocalDate(), // endDateの23:59:59時点
                "name"  // ここにnameを指定（適切な値に変更する必要があります）
        );


        // 取得したタスクをMapに変換
        Map<LocalDate, List<Tasks>> tasks = tasksList.stream() 
                .collect(Collectors.groupingBy(Tasks::getDate, Collectors.toList()));

        return tasks;
    }
}