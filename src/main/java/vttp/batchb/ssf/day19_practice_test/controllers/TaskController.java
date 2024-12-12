package vttp.batchb.ssf.day19_practice_test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.batchb.ssf.day19_practice_test.service.TaskService;

@Controller
@RequestMapping
public class TaskController {
    
    @Autowired
    private TaskService taskSvc;
    
    @GetMapping("/listing")
    public String getList(@RequestParam String status, Model model){
        return "";
    }
}
