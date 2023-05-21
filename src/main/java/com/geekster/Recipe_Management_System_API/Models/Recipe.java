package com.geekster.Recipe_Management_System_API.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;
    private String recipeName;
    private String recipeIngredients;
    private String recipeInstructions;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "recipe")
    List<Comment> comments;

}
