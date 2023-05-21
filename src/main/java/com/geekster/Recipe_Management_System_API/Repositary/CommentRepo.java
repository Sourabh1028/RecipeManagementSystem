package com.geekster.Recipe_Management_System_API.Repositary;

import com.geekster.Recipe_Management_System_API.Models.Comment;
import com.geekster.Recipe_Management_System_API.Models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

    List<Comment> findByRecipe(Recipe recipe);
}
