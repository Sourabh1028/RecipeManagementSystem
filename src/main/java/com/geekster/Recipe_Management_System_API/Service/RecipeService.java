package com.geekster.Recipe_Management_System_API.Service;


import com.geekster.Recipe_Management_System_API.Models.Recipe;
import com.geekster.Recipe_Management_System_API.Models.User;
import com.geekster.Recipe_Management_System_API.Repositary.RecipeRepo;
import com.geekster.Recipe_Management_System_API.Repositary.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepo recipeRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    public void addRecipe(Recipe recipe) {
        recipeRepo.save(recipe);
    }

    public List<Recipe> getAllRecipe(Integer userId) {
        User user = userRepo.findById(userId).get();
        return user.getRecipe();
    }

    public Recipe getRecipeByName(String name) {
        return recipeRepo.getByRecipeName(name);
    }

    public String deleteMyRecipe(String name, String email, String token) {
        //Check if any recipe is present with this name or not
        Recipe recipe=recipeRepo.findByRecipeName(name);
        if(recipe==null)return "No recipe exist with the name "+name;

        //Authenticate the user
        if(!authenticationService.authenticate(email,token)){
            return "User invalid!!";
        }

        //Get Authenticate User
        User user=userRepo.findByUserEmail(email);
        //Check if the recipe owner user and authenticate user is same then only we can delete the recipe

        //Get owner User
        User owner=recipe.getUser();
        if(!user.getUserId().equals(owner.getUserId())){
            return "Only owner can delete the recipe!!";
        }
        recipeRepo.delete(recipe);
        return "Recipe successfully deleted!!";
    }

    public ResponseEntity<String> editRecipe(String name, Recipe recipe) {
        Recipe recipe1=recipeRepo.findByRecipeName(name);

        if(recipe1==null){
            return new ResponseEntity<>("No recipe exist with name "+name, HttpStatus.NOT_FOUND);
        }

        if(recipe.getRecipeIngredients()!=null)recipe1.setRecipeIngredients(recipe.getRecipeIngredients());
        if (recipe.getRecipeInstructions()!=null)recipe1.setRecipeInstructions(recipe.getRecipeInstructions());

        return new ResponseEntity<>("Recipe successfully updated!!",HttpStatus.ACCEPTED);
    }

}
