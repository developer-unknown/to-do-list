package com.tasks.repository;

import com.tasks.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByCompleted(Boolean completed);
}
