package com.geekster.Recipe_Management_System_API.Service;

import com.geekster.Recipe_Management_System_API.Models.Comment;
import com.geekster.Recipe_Management_System_API.Models.Recipe;
import com.geekster.Recipe_Management_System_API.Models.User;
import com.geekster.Recipe_Management_System_API.Repositary.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    RecipeService recipeService;

    public String addComment(Comment comment) {
        Comment comment1 = commentRepo.save(comment);
        if(comment1 == null)
        {
            return "Comment not saved...!";
        }
        else
        {
            return "Comment saved...!";
        }
    }

    public List<Comment> getRecipeComments(String recipeName) {
        Recipe recipe=recipeService.getRecipeByName(recipeName);
        return commentRepo.findByRecipe(recipe);
    }

    public String saveCommentForRecipe(Comment comment, String recipeName, User user) {
        //Find the recipe with this name
        Recipe recipe=recipeService.getRecipeByName(recipeName);
        if(recipe==null)return "No recipe exist with the name "+recipeName;

        //Set the commented user and recipe
        comment.setUser(user);
        comment.setRecipe(recipe);
        commentRepo.save(comment);
        return "You post a comment on recipe "+recipeName;
    }

    public String deleteCommentById(Integer commentId) {
        commentRepo.deleteById(commentId);
        return "Comment successfully deleted!!!";
    }
}
