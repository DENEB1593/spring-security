package com.example.springsecurity.controller;

import com.example.springsecurity.model.User;
import org.springframework.core.style.ToStringCreator;

import java.time.LocalDateTime;

public class UserDto {

  private Long seq;
  private String email;
  private String birth;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public UserDto() { }

  public UserDto(User user) {
    this.seq = user.getSeq();
    this.email = user.getEmail();
    this.birth = user.getBirth();
    this.createdAt = user.getCreatedAt();
    this.updatedAt = user.getUpdatedAt();
  }

  public Long getSeq() {
    return seq;
  }

  public void setSeq(Long seq) {
    this.seq = seq;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getBirth() {
    return birth;
  }

  public void setBirth(String birth) {
    this.birth = birth;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return new ToStringCreator(this)
      .append("seq", this.seq)
      .append("email", this.email)
      .append("birth", this.birth)
      .append("createdAt", this.createdAt)
      .append("updatedAt", this.updatedAt)
      .toString();
  }
}
