package com.example.springsecurity.controller;

import org.springframework.core.style.ToStringCreator;

public class UpdateRequest {

  private final String password;

  private final String birth;

  public UpdateRequest(String password, String birth) {
    this.password = password;
    this.birth = birth;
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
      .append("password", password)
      .append("birth", birth)
      .toString();
  }
}
