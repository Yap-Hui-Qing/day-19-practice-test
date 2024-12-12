package vttp.batchb.ssf.day19_practice_test.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.batchb.ssf.day19_practice_test.models.Task;
import vttp.batchb.ssf.day19_practice_test.service.TaskService;

@Controller
@RequestMapping
public class TaskController {

    @Autowired
    private TaskService taskSvc;

    @GetMapping("/listing")
    public String getList(@RequestParam(required = false) String status, Model model) {
        List<Task> tasks = taskSvc.getTasks("tasks");
        List<Task> filteredTasks = new LinkedList<>();

        if (status == null || status.isEmpty()){
            filteredTasks = tasks;
        } else {
            if (status.equals("progress")){
                status = "in_progress";
            }
            for (Task task : tasks) {
                if (task.getStatus().equals(status))
                    filteredTasks.add(task);
            }
        }

        model.addAttribute("tasks", tasks);
        model.addAttribute("filtered", filteredTasks);
        return "listing";
    }
}
