package com.dmm.task.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dmm.task.entity.Task;
import com.dmm.task.service.TaskDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskDetails taskDetails;

    @GetMapping("/main2")
    public String showCalendar(@AuthenticationPrincipal Principal principal, Model model) {
        String username = principal.getName();
        LocalDate currentDate = LocalDate.now();

        List<List<LocalDate>> calendarMatrix = taskDetails.generateCalendarMatrix(currentDate, username);
        List<Task> userTasks = taskDetails.getUserTasks(currentDate, username);

        model.addAttribute("month", currentDate.getMonth().toString());
        model.addAttribute("matrix", calendarMatrix);
        model.addAttribute("tasks", userTasks);

        return "main";
    }

    @GetMapping("/main/create/{date}")
    public String createTask(@PathVariable String date, Model model) {

        return "create";
    }

    @GetMapping("/main/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        

        return "edit";
    }

}