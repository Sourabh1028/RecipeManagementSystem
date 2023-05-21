package com.geekster.Recipe_Management_System_API.Repositary;

import com.geekster.Recipe_Management_System_API.Models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepo extends JpaRepository<Recipe, Integer> {

    Recipe getByRecipeName(String name);

    Recipe findByRecipeName(String name);
}
