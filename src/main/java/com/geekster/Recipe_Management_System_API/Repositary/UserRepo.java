package com.geekster.Recipe_Management_System_API.Repositary;

import com.geekster.Recipe_Management_System_API.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    boolean existsByUserEmail(String userEmail);

    User findFirstByUserEmail(String userEmail);

    User findByUserEmail(String email);
}
