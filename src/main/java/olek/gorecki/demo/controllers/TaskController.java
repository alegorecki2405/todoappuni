package olek.gorecki.demo.controllers;

import olek.gorecki.demo.entities.Task;
import olek.gorecki.demo.models.TaskWriteModel;
import olek.gorecki.demo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task/all")
    public ResponseEntity<List<Task>> findAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @PostMapping("/task/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskWriteModel taskWriteModel) {
        return ResponseEntity.ok(taskService.createTask(taskWriteModel));
    }

    @PatchMapping("/task/{id}")
    public ResponseEntity<?> toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id);
        return ResponseEntity.noContent().build();
    }
}
