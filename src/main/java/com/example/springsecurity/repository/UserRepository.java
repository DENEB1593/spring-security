package com.example.springsecurity.repository;

import com.example.springsecurity.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
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
    SqlParameterSource parameter = new MapSqlParameterSource()
      .addValue("email", email);

    List<User> result = jdbcTemplate.query(sql, rowMapper, parameter);

    return Optional.ofNullable(result.isEmpty() ? null : result.get(0));
  }
  public User insert(User user) {
    String sql = "insert into users (email, password, birth, createdAt, updatedAt) values (?, ?, ?, ?, ?)";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(con -> {
      PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pstmt.setString(1, user.getEmail());
      pstmt.setString(2, user.getPassword());
      pstmt.setString(3, user.getBirth());
      pstmt.setTimestamp(4, Timestamp.from(Instant.now()));
      pstmt.setTimestamp(5, Timestamp.from(Instant.now()));
      return pstmt;
    }, keyHolder);

    Long seq = keyHolder.getKeyAs(Long.class);

    return new User.Builder(user)
      .seq(seq)
      .build();
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
