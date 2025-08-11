package com.tasks.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    @NotBlank(message = "Title Invalid")
    @Size(min = 3, max = 100)
    private String title;
    @Size(max = 255)
    private String description;
    @NotNull(message = "Completed Invalid")
    private Boolean completed;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate = LocalDateTime.now();
}
