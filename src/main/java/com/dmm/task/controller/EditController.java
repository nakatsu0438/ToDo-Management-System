package com.dmm.task.controller;

import com.dmm.task.model.entity.Tasks;
import com.dmm.task.repository.TaskRepository;
import com.dmm.task.service.AccountUserDetails;
import com.dmm.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditController {

	@Autowired
	private TaskRepository taskRepository;
	
    @Autowired
    private TaskService taskService;

    // カレンダー上のタスク名をクリックした際に編集画面を表示
    @GetMapping("/main/edit/{id}")
    public String taskEditorView(@PathVariable Long id, Model model) {
    	
        // タスクIDを使用して詳細情報を取得
        Tasks task = taskService.getTaskById(id);

        // 詳細情報をビューに渡す
        model.addAttribute("task", task);

        // 編集画面のテンプレート名を返す
        return "edit";
    }
    
	// タスクの更新
    @PostMapping("/main/edit/{id}")
    public String updateTask(@AuthenticationPrincipal AccountUserDetails user, @PathVariable Long id, @ModelAttribute Tasks taskForm) {
    	
    	// TaskServiceクラス内で定義された（TaskServiceクラス型）taskUpDateメソッドでタスクの更新
        taskService.taskUpDate(user, id, taskForm);
        
        // タスク更新が完了したらmainテンプレートにリダイレクト
        return "redirect:/main";
    }

    // タスクの削除
    @PostMapping("/main/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
    	
    	// taskRepositoryインターフェースにextendsしたJpaRepository（便利機能）に定義されたdeleteByIdメソッドでタスクを削除
        taskRepository.deleteById(id);
        
        // タスク削除が完了したらmainテンプレートにリダイレクト
        return "redirect:/main";
    }
    
}
