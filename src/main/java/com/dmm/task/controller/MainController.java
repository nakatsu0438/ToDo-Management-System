package com.dmm.task.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dmm.task.entity.Tasks;
import com.dmm.task.service.CalendarService;
import com.dmm.task.service.TaskService;

@Controller
public class MainController {

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/main")
    public String showCalendar(Model model) {
    	// 3. その月の1日のLocalDateを取得する
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        // 月の最終日を取得
        LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        // カレンダーを生成
        List<List<LocalDate>> calendar = calendarService.generateCalendar(firstDayOfMonth, lastDayOfMonth);

        Map<LocalDate, List<Tasks>> tasks = taskService.getTasks(firstDayOfMonth, lastDayOfMonth);

        model.addAttribute("matrix", calendar);
        model.addAttribute("tasks", tasks);

        return "main";
    }
}