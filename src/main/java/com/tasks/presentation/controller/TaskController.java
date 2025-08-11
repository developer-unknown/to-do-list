package com.tasks.presentation.controller;

import com.tasks.presentation.dto.TaskDTO;
import com.tasks.service.implementation.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/task/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("create")
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO taskDTO) {
        return new ResponseEntity<>(this.taskService.createTask(taskDTO), HttpStatus.CREATED);
    }

    @GetMapping("find")
    public ResponseEntity<List<TaskDTO>> findAll(@RequestParam(required = false) Boolean completed) {
        if (completed != null) {
            return new ResponseEntity<>(this.taskService.findByCompleted(completed), HttpStatus.OK);
        }
        return new ResponseEntity<>(this.taskService.findAll(), HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.taskService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Integer> deleteTask(@PathVariable Long id) {
        this.taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody @Valid TaskDTO taskDTO) {
        return new ResponseEntity<>(this.taskService.updateTask(id, taskDTO), HttpStatus.OK);
    }
}
