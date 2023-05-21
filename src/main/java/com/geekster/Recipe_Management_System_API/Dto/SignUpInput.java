package com.geekster.Recipe_Management_System_API.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpInput {

    @NotBlank
    private String userFirstName;
    @NotEmpty
    private String userLastName;
    @Email
    private String userEmail;
    @NotEmpty
    private String userPassword;
    @NotEmpty
    private String userContact;
}
