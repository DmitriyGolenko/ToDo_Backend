package com.app.TODO_backend.controller;

import com.app.TODO_backend.entity.Task;
import com.app.TODO_backend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @GetMapping()
    public List<Task> getTasks() {
        return taskRepository.getAllBy();
    }

    @PostMapping()
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable(value = "id") long id) {
        return taskRepository.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task putTask(@PathVariable(value = "id") long id, @RequestBody Task task) {
        taskRepository.updateTaskById(id, task.getDate(), task.isDone(), task.getDescription());
        return taskRepository.getTaskById(id);
    }
    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable(value = "id") long id){
        return taskRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void markAsDone(@PathVariable long id, @RequestBody Boolean requestBody) {
        taskRepository.markTask(id,requestBody);
    }
}
