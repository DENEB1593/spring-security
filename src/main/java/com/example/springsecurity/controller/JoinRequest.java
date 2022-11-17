package com.example.springsecurity.controller;

import org.springframework.core.style.ToStringCreator;

public class JoinRequest {

  private String email;
  private String password;
  private String birth;

  public JoinRequest() { }

  public JoinRequest(String email,
                     String password,
                     String birth) {
    this.email = email;
    this.password = password;
    this.birth = birth;
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

  @Override
  public String toString() {
    return new ToStringCreator(this)
      .append("email", this.email)
      .append("password", this.password)
      .append("birth", this.birth)
      .toString();
  }
}
