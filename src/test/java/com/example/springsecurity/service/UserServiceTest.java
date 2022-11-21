package com.example.springsecurity.service;

import com.example.springsecurity.controller.JoinRequest;
import com.example.springsecurity.controller.UpdateRequest;
import com.example.springsecurity.model.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

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
    User saved = userService.findByEmail(email).orElse(null);

    assertThat(saved).isNotNull();
    assertThat(saved.getEmail()).isEqualTo(email);
  }

  @Test
  @Order(3)
  @DisplayName("사용자_수정")
  public void updateTest() {
    User before = userService.findByEmail(email).orElse(null);


    assertThat(before).isNotNull();

    String modifyPassword = "1111";
    String modifyBirth = "19991205";

    UpdateRequest request = new UpdateRequest(
      before.getSeq(),
      modifyPassword,
      modifyBirth);

    userService.update(request);

    User after = userService.findByEmail(email).orElse(null);

    SoftAssertions bundle = new SoftAssertions();
    bundle.assertThat(after).isNotNull();
    bundle.assertThat(after.getPassword()).isEqualTo(modifyPassword);
    bundle.assertThat(after.getBirth()).isEqualTo(modifyBirth);
    bundle.assertAll();
  }

}