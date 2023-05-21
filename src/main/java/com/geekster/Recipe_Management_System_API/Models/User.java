package com.geekster.Recipe_Management_System_API.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotBlank
    private String userFirstName;
    @NotEmpty
    private String userLastName;
    @Column(unique = true)
    private String userEmail;
    @NotBlank
    private String userPassword;
    @NotEmpty
    private String userContact;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Recipe> recipe;

    public User(String userFirstName, String userLastName, String userEmail, String userPassword, String userContact) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userContact = userContact;
    }
}
