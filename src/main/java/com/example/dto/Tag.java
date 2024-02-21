package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Tag {
    private Integer id;
    @NotBlank(message = "Name field must has a value")
    @Size(min = 5,max = 200, message = "name must be between 5 and 200 characters")
    private String name;
    private LocalDateTime createdDate;
}
