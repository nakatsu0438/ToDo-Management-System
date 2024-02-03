package com.dmm.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    
    // mainコントローラー
	public Map<LocalDate, List<Tasks>> getTasks(@AuthenticationPrincipal AccountUserDetails user) {
		
		// 現在の月の初日のLocalDateを取得する
		LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
		// 月の最終日を取得
		LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
		
		List<Tasks> tasksList;

	    if ("admin-name".equals(user.getName())) {
	        // adminユーザーの場合は全てのタスクを取得
	        tasksList = taskRepository.findAllByDateBetween (
	        		firstDayOfMonth.atStartOfDay().toLocalDate(),
	                lastDayOfMonth.atTime(23, 59, 59).toLocalDate()
	        );
	    } else {
	        // 通常のユーザーの場合はユーザー名を条件にしてタスクを取得
	        tasksList = taskRepository.findByDateBetween(
	                firstDayOfMonth.atStartOfDay().toLocalDate(),
	                lastDayOfMonth.atTime(23, 59, 59).toLocalDate(),
	                user.getName()
	        );
	    }
		
		return tasksList.stream()
				.collect(Collectors.groupingBy(Tasks::getDate, Collectors.toList()));
	}


	// createコントローラー
	public Tasks taskCreate(@AuthenticationPrincipal AccountUserDetails user, Tasks tasksForm) {
	
		Tasks tasks = new Tasks();
    	tasks.setTitle(tasksForm.getTitle()); 
    	tasks.setName(user.getName()); // tasksFormではなくuserであることに注意
    	tasks.setText(tasksForm.getText());   
    	tasks.setDate(tasksForm.getDate());
    	tasks.setDone(tasksForm.isDone());
        
    	// フォームから受け取ったデータを保存
    	taskRepository.save(tasks);
		
		return tasks;
	}


	// editコントローラー
	public Tasks getTaskById(Long id) {
		
		// タスクIDを使用してタスクを取得
		Tasks tasks = taskRepository.findById(id).get();
		
		return tasks;
		
	}

	public Tasks taskUpDate(@AuthenticationPrincipal AccountUserDetails user, Long id, Tasks tasksForm) {

	    // タスクIDを使用して既存のタスクを取得
	    Tasks taskUpDate = taskRepository.findById(id).get();

	    // 既存のタスクを更新
	    if (taskUpDate != null) {
	    	
	    	taskUpDate.setTitle(tasksForm.getTitle());
	    	taskUpDate.setName(user.getName()); // tasksFormではなくuserであることに注意
	    	taskUpDate.setText(tasksForm.getText());
	    	taskUpDate.setDate(tasksForm.getDate());
	    	taskUpDate.setDone(tasksForm.isDone());
	    	
	    	// 更新を保存
	    	taskRepository.save(taskUpDate);
	    }
	    
	    return taskUpDate;
	}

}