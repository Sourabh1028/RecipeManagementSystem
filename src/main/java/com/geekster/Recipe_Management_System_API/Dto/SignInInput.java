package com.geekster.Recipe_Management_System_API.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInInput {

    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;

    @NotEmpty
    private String password;

}
