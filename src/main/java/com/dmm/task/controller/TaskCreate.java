package com.dmm.task.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dmm.task.model.entity.Tasks;
import com.dmm.task.service.TaskService;

@Controller
public class TaskCreate {
	// カレンダーの日付をクリックしたときの詳細画面
    @GetMapping("/main/create/{selectedDate}")
    public String showTaskDetails(@PathVariable String selectedDate, Model model) {
        // 選択された日付をLocalDateに変換
        LocalDate selectedLocalDate = LocalDate.parse(selectedDate);
        

        model.addAttribute("task", selectedLocalDate);
        return "create";
    }
}
