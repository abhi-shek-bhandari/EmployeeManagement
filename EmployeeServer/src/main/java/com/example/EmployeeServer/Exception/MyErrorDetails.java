package com.example.EmployeeServer.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MyErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;

}
