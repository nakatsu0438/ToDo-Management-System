package com.dmm.task.controller;

import com.dmm.task.model.entity.Tasks;
import com.dmm.task.service.AccountUserDetails;
import com.dmm.task.service.TaskService;

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
    private TaskService taskService;
    
    // カレンダー上の日付をクリックした際に登録画面を表示
    @GetMapping("/main/create/{selectedDate}")
    public String showTaskDetails(@PathVariable String selectedDate, Model model) {
    	
    	// 選択された日付をLocalDateに変換
    	LocalDate selectedLocalDate = LocalDate.parse(selectedDate);
    	
    	model.addAttribute("tasks", new Tasks());
    	model.addAttribute("tasks", selectedLocalDate);
    	return "create";
    }

    @PostMapping("/main/create") // @AuthenticationPrincipalでAccountUserDetailsからユーザー情報を取得
    public String createTask(@AuthenticationPrincipal AccountUserDetails user, Tasks tasksForm) {

    	// TaskServiceクラス内で定義さた（TaskServiceクラス型）のtaskCreateメソッドを使用してタスクを登録
    	taskService.taskCreate(user, tasksForm);

    	// mainテンプレートにリダイレクト
        return "redirect:/main";
    }


}