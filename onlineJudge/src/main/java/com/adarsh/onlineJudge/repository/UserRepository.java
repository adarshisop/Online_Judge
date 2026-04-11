package com.adarsh.onlineJudge.repository;

import com.adarsh.onlineJudge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

// to get user id by email
Optional<User> findByEmail(String email);
Optional<User> findByUsername(String username);
}
