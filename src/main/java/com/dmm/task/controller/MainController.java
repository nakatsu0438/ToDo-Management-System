package com.dmm.task.controller;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.dmm.task.model.entity.Tasks;
//import com.dmm.task.repository.TaskRepository;
import com.dmm.task.service.AccountUserDetails;
import com.dmm.task.service.CalendarService;
import com.dmm.task.service.TaskService;
//import com.dmm.task.service.TaskService;

@Controller
public class MainController {

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/main")
    public String showCalendar(Model model, @AuthenticationPrincipal AccountUserDetails user) {
    	
        // その月の初日を取得
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        System.out.println("firstDayOfMonth : " + firstDayOfMonth);
        // 月の最終日を取得
        LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        System.out.println("lastDayOfMonth : " + lastDayOfMonth);
        
        // 前月と次月の日付を計算
        LocalDate prev = firstDayOfMonth.minusMonths(1);
        System.out.println("prev : " + prev);
        LocalDate next = firstDayOfMonth.plusMonths(1);
        System.out.println("next : " + next);
        
        // カレンダーを生成
        List<List<LocalDate>> calendar = calendarService.generateCalendar(firstDayOfMonth, lastDayOfMonth);
        
        // TaskServiceからタスクを取得
        Map<LocalDate, List<Tasks>> tasks = taskService.getTasks(user);

        // mainテンプレートに渡すデータを設定
        model.addAttribute("prev", prev);
        model.addAttribute("next", next);
        model.addAttribute("month", calendarService.displayYearAndMonth(firstDayOfMonth));
        model.addAttribute("matrix", calendar);
        model.addAttribute("tasks", tasks);
        
        
        // 遷移先のテンプレート名を返す
        return "main";
    }
    
}