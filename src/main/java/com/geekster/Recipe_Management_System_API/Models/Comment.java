package com.geekster.Recipe_Management_System_API.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "fk_recipeId_ID")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "fk_userId")
    private User user;

}
