package com.example.springsecurity.controller;

import org.springframework.core.style.ToStringCreator;

public class UpdateRequest {

  private final Long seq;

  private final String password;

  private final String birth;

  public UpdateRequest(Long seq, String password, String birth) {
    this.seq = seq;
    this.password = password;
    this.birth = birth;
  }

  public Long getSeq() {
    return seq;
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
      .append("seq", seq)
      .append("password", password)
      .append("birth", birth)
      .toString();
  }
}
