package com.geekster.Recipe_Management_System_API.Service;

import com.geekster.Recipe_Management_System_API.Dto.SignInInput;
import com.geekster.Recipe_Management_System_API.Dto.SignInOutput;
import com.geekster.Recipe_Management_System_API.Dto.SignUpInput;
import com.geekster.Recipe_Management_System_API.Dto.SignUpOutput;
import com.geekster.Recipe_Management_System_API.Models.AuthenticationToken;
import com.geekster.Recipe_Management_System_API.Models.Comment;
import com.geekster.Recipe_Management_System_API.Models.Recipe;
import com.geekster.Recipe_Management_System_API.Models.User;
import com.geekster.Recipe_Management_System_API.Repositary.AuthRepo;
import com.geekster.Recipe_Management_System_API.Repositary.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    CommentService commentService;

    @Autowired
    AuthenticationService authService;

    @Autowired
    AuthRepo authRepo;

    @Autowired
    RecipeService recipeService;


    public boolean checkUser(String userEmail) {
        return userRepo.existsByUserEmail(userEmail);
    }

    public SignUpOutput signUp(SignUpInput signUpInput) {


        //check if user exists or not based on email
        User user = userRepo.findByUserEmail(signUpInput.getUserEmail());

        if(user != null)
        {
            throw new IllegalStateException("User already exists!!!!...sign in instead");
        }

//      encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signUpInput.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        signUpInput.setUserPassword(encryptedPassword);

        user = new User(signUpInput.getUserFirstName(),signUpInput.getUserLastName(), signUpInput.getUserEmail(), encryptedPassword, signUpInput.getUserContact());

        userRepo.save(user);
        return new SignUpOutput(HttpStatus.OK,"User account created successfully");

    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;

    }

    public SignInOutput signIn(SignInInput signInDto) {
        //check if user exists or not based on email
        User user = userRepo.findFirstByUserEmail(signInDto.getEmail());

        if(user == null)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(user.getUserPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        AuthenticationToken token = new AuthenticationToken(user);

        authService.saveToken(token);

        //set up output response

        return new SignInOutput("Authentication Successfull !!!", token.getToken());


    }

    public void updateUser(User user , String token) {
        User originalUser = authRepo.findFirstByToken(token).getUser();


        if(!(user.getUserFirstName().isEmpty())){
            originalUser.setUserFirstName(user.getUserFirstName());
        }
        if((user.getUserLastName()!=null)){
            originalUser.setUserLastName(user.getUserLastName());
        }
        if((user.getUserPassword()!=null)){
            String encryptedPassword = null;

            try {
                encryptedPassword = encryptPassword(user.getUserPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            originalUser.setUserPassword(encryptedPassword);
        }

        if((user.getUserContact()!=null)){
            Pattern p = Pattern.compile("\\d{2}-\\d{10}");

            Matcher m = p.matcher(user.getUserContact());
            if( (m.find() && m.group().equals(user.getUserContact()))){
                originalUser.setUserContact(user.getUserContact());

            }else{
                throw new IllegalStateException("Enter correct details");
            }

        }

        if((user.getUserEmail()!=null)){
            Pattern p = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}");

            Matcher m = p.matcher(user.getUserEmail());
            if( (m.find() && m.group().equals(user.getUserEmail()))){
                originalUser.setUserEmail(user.getUserEmail());

            }else{
                throw new IllegalStateException("Enter correct details");
            }
        }

        userRepo.save(originalUser);

    }

    public String deleteMyRecipe(String name, String email, String token) {
        return recipeService.deleteMyRecipe(name, email, token);
    }

    public String commentOnRecipe(Comment comment, String recipeName, String email, String token) {
        if(!authService.authenticate(email,token)){
            return "User invalid!!";
        }

        User user=userRepo.findByUserEmail(email);
        return commentService.saveCommentForRecipe(comment,recipeName,user);
    }

    public String deleteCommentById(String recipeName, Integer commentId, String email, String token) {
        //Authenticate the user
        if(!authService.authenticate(email,token)){
            return "User invalid!!";
        }
        User user=userRepo.findByUserEmail(email);

        //Check owner user and given user is same or not
        User owner=recipeService.getRecipeByName(recipeName).getUser();
        if(!user.getUserId().equals(owner.getUserId())){
            return "Only owner can delete any comments!!";
        }
        return commentService.deleteCommentById(commentId);
    }

    public ResponseEntity<String> editRecipe(String name, Recipe recipe) {
        return recipeService.editRecipe(name,recipe);
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
