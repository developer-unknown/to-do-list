package com.tasks.service.interfaces;

import com.tasks.presentation.dto.TaskDTO;

import java.util.List;

public interface ITaskService {

    TaskDTO createTask(TaskDTO taskDTO);
    List<TaskDTO> findAll();
    List<TaskDTO> findByCompleted(Boolean completed);
    TaskDTO findById(Long id);
    void deleteTask(Long id);
    TaskDTO updateTask(Long id, TaskDTO taskDTO);
}
