package com.geekster.Recipe_Management_System_API.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpOutput {

    private HttpStatus status;

    private String message;
}
