package com.example.springsecurity.repository;

import com.example.springsecurity.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

  private JdbcTemplate jdbcTemplate;


  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Optional<User> findByEmail(String email) {
    String sql = "select * from users where email = ?";
    List<User> result = jdbcTemplate.query(sql, rowMapper, new Object[]{email});
    return Optional.ofNullable(result.isEmpty() ? null : result.get(0));
  }

  public User insert(User user) {
    return null;
  }

  private static RowMapper<User> rowMapper = (rs, rowNum) ->
    new User.Builder()
    .seq(rs.getLong("seq"))
    .email(rs.getString("email"))
    .password(rs.getString("password"))
    .birth(rs.getString("birth"))
    .createdAt(rs.getObject("createdAt", LocalDateTime.class))
    .updatedAt(rs.getObject("updatedAt", LocalDateTime.class))
    .build();
}
