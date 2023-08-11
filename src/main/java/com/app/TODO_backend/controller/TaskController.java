package com.app.TODO_backend.controller;

import com.app.TODO_backend.entity.Task;
import com.app.TODO_backend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskRepository.getAllBy();
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable(value = "id") long id) {
        return taskRepository.getTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    public Task putTask(@PathVariable(value = "id") long id, @RequestBody Task task) {
        taskRepository.updateTaskById(id, task.getDate(), task.isDone(), task.getDescription());
        return taskRepository.getTaskById(id);
    }
    @DeleteMapping("/tasks/{id}")
    public Task deleteTask(@PathVariable(value = "id") long id){
        return taskRepository.deleteById(id);
    }

    @PatchMapping("/tasks/{id}")
    public void markAsDone(@PathVariable long id, @RequestBody Boolean requestBody) {
        taskRepository.markTask(id,requestBody);
    }
}
