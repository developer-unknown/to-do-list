package com.tasks.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;
}
