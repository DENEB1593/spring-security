package com.example.springsecurity.service;

import com.example.springsecurity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  private final static Logger log = LoggerFactory.getLogger(UserService.class);

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  public void 사용자_추가() {

  }

  @Test
  public void 사용자_이메일_조회() {

  }

  @Test
  public void 사용자_수정() {

  }

}