package com.geekster.Recipe_Management_System_API.Controller;

import com.geekster.Recipe_Management_System_API.Dto.SignInInput;
import com.geekster.Recipe_Management_System_API.Dto.SignInOutput;
import com.geekster.Recipe_Management_System_API.Dto.SignUpInput;
import com.geekster.Recipe_Management_System_API.Dto.SignUpOutput;
import com.geekster.Recipe_Management_System_API.Models.Comment;
import com.geekster.Recipe_Management_System_API.Models.Recipe;
import com.geekster.Recipe_Management_System_API.Models.User;
import com.geekster.Recipe_Management_System_API.Service.AuthenticationService;
import com.geekster.Recipe_Management_System_API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authService;

    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/signUp")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpDto){
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInOutput signIn(@RequestBody SignInInput signInDto){
        return userService.signIn(signInDto);
    }

    @DeleteMapping("recipe/{name}")
    public String deleteRecipe(@PathVariable String name,@RequestParam String email,@RequestParam String token){
        return userService.deleteMyRecipe(name,email,token);
    }

    @PostMapping("/recipe/comment/{RecipeName}")
    public String addComment(@Valid @RequestBody Comment comment, @PathVariable String recipeName, @RequestParam String email, @RequestParam String token){
        return userService.commentOnRecipe(comment,recipeName,email,token);
    }

    @DeleteMapping("/recipe/{recipeId}/comment/{commentId}")
    public String deleteComment(@PathVariable String recipeName, @PathVariable Integer commentId,@RequestParam String email,@RequestParam String token){
        return userService.deleteCommentById(recipeName,commentId,email,token);
    }

    @PutMapping("recipe/{name}")
    public ResponseEntity<String> editRecipe(@PathVariable String name, @RequestBody Recipe recipe){
        return userService.editRecipe(name,recipe);
    }


}
