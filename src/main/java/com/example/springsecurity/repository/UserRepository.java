package com.example.springsecurity.repository;

import com.example.springsecurity.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  public User findByEmail(String email) {
    return null;
  }

  public User insert(User user) {
    return null;
  }
}
