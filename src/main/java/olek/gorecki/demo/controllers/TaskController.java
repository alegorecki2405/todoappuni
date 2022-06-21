package olek.gorecki.demo.controllers;

import olek.gorecki.demo.service.TaskService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
}
