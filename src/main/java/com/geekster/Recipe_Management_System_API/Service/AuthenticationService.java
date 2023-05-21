package com.geekster.Recipe_Management_System_API.Service;

import com.geekster.Recipe_Management_System_API.Models.AuthenticationToken;
import com.geekster.Recipe_Management_System_API.Repositary.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    AuthRepo authRepo;

    public void saveToken(AuthenticationToken token) {
        authRepo.save(token);
    }

    public boolean authenticate(String email, String token) {

        if(token==null && email==null){
            return false;
        }

        AuthenticationToken authToken = authRepo.findFirstByToken(token);

        if(authToken==null){
            return false;
        }

        String expectedEmail = authToken.getUser().getUserEmail();


        return expectedEmail.equals(email);
    }

}
