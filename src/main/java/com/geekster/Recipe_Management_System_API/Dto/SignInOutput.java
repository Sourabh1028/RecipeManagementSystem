package com.geekster.Recipe_Management_System_API.Dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SignInOutput {

    private String status;
    private String token;

}
