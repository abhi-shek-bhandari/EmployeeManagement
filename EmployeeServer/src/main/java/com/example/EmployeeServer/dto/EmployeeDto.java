package com.example.EmployeeServer.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class EmployeeDto {

        private String firstName;
        private String lastName;

        @Column(unique = true)
        private String email;

        @Column(unique = true)
        private String mobile;
}
