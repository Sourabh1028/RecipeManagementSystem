package com.geekster.Recipe_Management_System_API.Repositary;

import com.geekster.Recipe_Management_System_API.Models.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<AuthenticationToken, Integer> {

    AuthenticationToken findFirstByToken(String token);
}
