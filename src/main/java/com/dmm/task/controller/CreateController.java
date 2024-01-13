package com.dmm.task.controller;

import com.dmm.task.model.entity.Tasks;
import com.dmm.task.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
public class CreateController {

    @Autowired
    private TaskService taskService;
    
    // カレンダーの日付をクリックしたときの詳細画面
    @GetMapping("/main/create/{selectedDate}")
    public String showTaskDetails(@PathVariable String selectedDate, Model model) {
    	// フォームの初期化
    	// 選択された日付をLocalDateに変換
    	LocalDate selectedLocalDate = LocalDate.parse(selectedDate);
    	
    	model.addAttribute("task", new Tasks());
    	model.addAttribute("task", selectedLocalDate);
    	return "create";
    }

    
    @PostMapping("/main")
    public String createTask(@ModelAttribute("task") Tasks taskForm) {
        // フォームから受け取ったデータを保存
        taskService.saveTask(taskForm);

        return "redirect:/main";  // mainにリダイレクト
    }

}