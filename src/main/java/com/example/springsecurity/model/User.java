package com.example.springsecurity.model;

import com.example.springsecurity.controller.JoinRequest;
import org.springframework.core.style.ToStringCreator;

import java.time.LocalDateTime;

public class User {
  private Long seq;
  private String email;
  private String password;
  private String birth;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public User() { }

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
}
