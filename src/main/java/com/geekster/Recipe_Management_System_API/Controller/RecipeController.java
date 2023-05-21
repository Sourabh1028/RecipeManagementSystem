package com.geekster.Recipe_Management_System_API.Controller;

import com.geekster.Recipe_Management_System_API.Models.Recipe;
import com.geekster.Recipe_Management_System_API.Service.RecipeService;
import com.geekster.Recipe_Management_System_API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    UserService userService;

    @PostMapping("/add/{userEmail}")
    public ResponseEntity<String> addRecipe(@PathVariable String userEmail, @RequestBody Recipe recipe){
        String response;
        HttpStatus status;

        if (userService.checkUser(userEmail)) {
            recipeService.addRecipe(recipe);
            response = "Recipe added successfully";
            status = HttpStatus.ACCEPTED;
        }else{
            response = "You're not an user.... ";
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<String>(response, status);
    }

    @GetMapping("getAll")
    public List<Recipe> getAllRecipe(@PathVariable Integer userId){
        return recipeService.getAllRecipe(userId);
    }

    @GetMapping("/{name}")
    public Recipe getRecipeByName(@PathVariable String name){
        return recipeService.getRecipeByName(name);
    }

}
