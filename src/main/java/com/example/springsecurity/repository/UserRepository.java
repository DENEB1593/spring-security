package com.example.springsecurity.repository;

import com.example.springsecurity.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private JdbcTemplate jdbcTemplate;

  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public User findByEmail(String email) {
    return null;
  }

  public User insert(User user) {
    return null;
  }
}
