package com.tasks.service.implementation;

import com.tasks.entities.TaskEntity;
import com.tasks.exceptions.TaskNotFound;
import com.tasks.presentation.dto.TaskDTO;
import com.tasks.repository.ITaskRepository;
import com.tasks.service.interfaces.ITaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        ModelMapper modelMapper = new ModelMapper();
        TaskEntity task = modelMapper.map(taskDTO, TaskEntity.class);
        this.taskRepository.save(task);
        return taskDTO;
    }

    @Override
    public List<TaskDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        return this.taskRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findByCompleted(Boolean completed) {
        ModelMapper modelMapper = new ModelMapper();
        return this.taskRepository.findByCompleted(completed)
                .stream()
                .map(entity -> modelMapper.map(entity, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO findById(Long id) {
        TaskEntity task = this.findTask(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public void deleteTask(Long id) {
        TaskEntity task = this.findTask(id);
        this.taskRepository.delete(task);
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        TaskEntity task = this.findTask(id);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setCompleted(task.getCompleted());
        task.setCreateDate(task.getCreateDate());
        this.taskRepository.save(task);
        return taskDTO;
    }

    private TaskEntity findTask(Long id) {
        return this.taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFound("Task not found"));
    }
}
