package com.tasks.exceptions;

public class TaskNotFound extends RuntimeException {

    public TaskNotFound(String message) {
        super(message);
    }
}
