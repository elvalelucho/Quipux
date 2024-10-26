package com.luismolina.quipux.database.repos;

import com.luismolina.quipux.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
