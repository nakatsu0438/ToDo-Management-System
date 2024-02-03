package com.dmm.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public Map<LocalDate, List<Tasks>> getTasks(
            @AuthenticationPrincipal AccountUserDetails user,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        // 日付がnullの場合は現在の日付を使用
        if (date == null) {
            date = LocalDate.now();
        }

        // 該当月の初日を取得
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);

        // 該当月の最終日を取得
        LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth());

        // 前月の初日を取得
        LocalDate firstDayOfPreviousMonth = firstDayOfMonth.minusMonths(1);

        // 前月の最終日を取得
        LocalDate lastDayOfPreviousMonth = firstDayOfMonth.minusDays(1);

        // 翌月の初日を取得
        LocalDate firstDayOfNextMonth = lastDayOfMonth.plusDays(1);

        // 翌月の最終日を取得
        LocalDate lastDayOfNextMonth = lastDayOfMonth.plusMonths(1);

        // タスクを取得する範囲を決定
        LocalDate startDate = firstDayOfPreviousMonth;
        LocalDate endDate = lastDayOfNextMonth;

        // タスクを取得
        List<Tasks> tasksList;
        if ("admin-name".equals(user.getName())) {
            tasksList = taskRepository.findAllByDateBetween(
                    startDate.atStartOfDay().toLocalDate(),
                    endDate.atTime(23, 59, 59).toLocalDate()
            );
        } else {
            tasksList = taskRepository.findByDateBetween(
                    startDate.atStartOfDay().toLocalDate(),
                    endDate.atTime(23, 59, 59).toLocalDate(),
                    user.getName()
            );
        }

        // タスクを日付ごとにグループ化して返す
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