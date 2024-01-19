package com.dmm.task.controller;

import com.dmm.task.model.entity.Tasks;
import com.dmm.task.repository.TaskRepository;
import com.dmm.task.service.AccountUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDate;

@Controller
public class CreateController {

    @Autowired
    private TaskRepository taskRepository;
    
    // カレンダーの日付をクリックしたときの詳細画面
    @GetMapping("/main/create/{selectedDate}")
    public String showTaskDetails(@PathVariable String selectedDate, Model model) {
    	
    	// 選択された日付をLocalDateに変換
    	LocalDate selectedLocalDate = LocalDate.parse(selectedDate);
    	
    	model.addAttribute("tasks", new Tasks());
    	model.addAttribute("tasks", selectedLocalDate);
    	return "create";
    }

    @PostMapping("/main/create") // @AuthenticationPrincipalでAccountUserDetailsからユーザー情報を取得
    public String createTask(Tasks tasksForm, @AuthenticationPrincipal AccountUserDetails user) {
    	
    	Tasks tasks = new Tasks();
    	tasks.setTitle(tasksForm.getTitle()); 
    	tasks.setName(user.getName()); // tasksFormではなくuserであることに注意
    	tasks.setText(tasksForm.getText());   
    	tasks.setDate(tasksForm.getDate());
    	tasks.setDone(tasksForm.isDone());
        
        // 確認用
        System.out.println("Title: " + tasksForm.getTitle());
        System.out.println("Name: " + tasksForm.getName());
        System.out.println("Text: " + tasksForm.getText());
        System.out.println("Date: " + tasksForm.getDate());
        System.out.println("Tasks Object: " + tasks);
        
        // フォームから受け取ったデータを保存
        taskRepository.save(tasks);

        return "redirect:/main";  // mainにリダイレクト
    }


}