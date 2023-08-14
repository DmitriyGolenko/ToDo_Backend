package com.app.TODO_backend.controller;

import com.app.TODO_backend.entity.Task;
import com.app.TODO_backend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    @GetMapping()
    public List<Task> getTasks() {
        return taskRepository.getAllBy();
    }

    @PostMapping()
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable(value = "id") long id) {
        Optional<Task> task = taskRepository.getTaskById(id);
        if (task.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putTask(@PathVariable(value = "id") long id, @RequestBody Task task) {
        taskRepository.updateTaskById(id, task.getDate(), task.isDone(), task.getDescription());
        return ResponseEntity.ok().build();
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
