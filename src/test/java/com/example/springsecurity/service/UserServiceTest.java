package com.example.springsecurity.service;

import com.example.springsecurity.controller.JoinRequest;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.UserRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

  private final static Logger log = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserService userService;

  private String email;

  private String password;

  private String birth;

  @BeforeAll
  public void setUp() {
    email = "service_test@email.com";
    password = "1234";
    birth = "20000817";
  }

  @Test
  @Order(1)
  @DisplayName("사용자_추가")
  public void joinTest() {
    JoinRequest request = new JoinRequest(email, password, birth);
    User saved = userService.join(request);

    SoftAssertions bundle = new SoftAssertions();
    bundle.assertThat(saved.getEmail()).isEqualTo(email);
    bundle.assertThat(saved.getPassword()).isEqualTo(password);
    bundle.assertThat(saved.getBirth()).isEqualTo(birth);
    bundle.assertAll();
  }

  @Test
  @Order(2)
  @DisplayName("사용자_이메일_조회")
  public void findByEmailTest() {

  }

  @Test
  @Order(3)
  @DisplayName("사용자_수정")
  public void updateTest() {

  }

}