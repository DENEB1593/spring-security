package com.example.springsecurity.model;

import com.example.springsecurity.controller.JoinRequest;
import com.example.springsecurity.controller.UpdateRequest;
import org.springframework.core.style.ToStringCreator;

import java.time.LocalDateTime;

public class User {
  private Long seq;
  private String email;
  private String password;
  private String birth;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private User() { }

  public User(Long seq,
             String email,
             String password,
             String birth,
             LocalDateTime createdAt,
             LocalDateTime updatedAt) {
    this.seq = seq;
    this.email = email;
    this.password = password;
    this.birth = birth;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public User(JoinRequest request) {
    this.email = request.getEmail();
    this.password = request.getPassword();
    this.birth = request.getBirth();
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public User(UpdateRequest request) {
    this.password = request.getPassword();
    this.birth = request.getBirth();
    this.updatedAt = LocalDateTime.now();
  }


  public Long getSeq() {
    return seq;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getBirth() {
    return birth;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public String toString() {
    return new ToStringCreator(this)
      .append("seq", this.seq)
      .append("email", this.email)
      .append("password", this.password)
      .append("birth", this.birth)
      .append("createdAt", this.createdAt)
      .append("updatedAt", this.updatedAt)
      .toString();
  }

  static public class Builder {

    private Long seq;

    private String email;

    private String password;

    private String birth;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Builder() { }

    public Builder(User user) {
      this.seq = user.seq;
      this.email = user.email;
      this.password = user.password;
      this.birth = user.birth;
      this.createdAt = user.createdAt;
      this.updatedAt = user.updatedAt;
    }

    public Builder seq(Long seq) {
      this.seq = seq;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Builder birth(String birth) {
      this.birth = birth;
      return this;
    }

    public Builder createdAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder updatedAt(LocalDateTime updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public User build() {
      return new User(seq, email, password, birth, createdAt, updatedAt);
    }

  }
}
