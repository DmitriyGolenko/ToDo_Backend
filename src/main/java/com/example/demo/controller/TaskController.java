package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
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
