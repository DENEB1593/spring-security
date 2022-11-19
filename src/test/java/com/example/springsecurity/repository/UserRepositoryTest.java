package com.example.springsecurity.repository;

import com.example.springsecurity.controller.JoinRequest;
import com.example.springsecurity.model.User;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

  private final static Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

  @Autowired
  private UserRepository userRepository;

  private String email;
  private String password;
  private String birth;

  @BeforeAll
  public void setUp() {
    email = "lee@gmail.com";
    password = "1234";
    birth = "19930101";
  }

  @Test
  @Order(1)
  public void 사용자_추가() {
    JoinRequest request = new JoinRequest(email, password, birth);
    User user = new User(request);

    User saved = userRepository.insert(user);

    assertThat(user).usingRecursiveComparison()
      .ignoringActualNullFields()
      .isEqualTo(saved);
//    SoftAssertions softly = new SoftAssertions();
//    softly.assertThat(email).isEqualTo(saved.getEmail());
//    softly.assertThat(password).isEqualTo(saved.getPassword());
//    softly.assertThat(birth).isEqualTo(saved.getBirth());
//    softly.assertAll();
  }

  @Test
  @Order(2)
  public void 사용자_이메일_조회_존재() {
    User user = userRepository.findByEmail(email).orElse(null);

    assertThat(user).isNotNull();
    assertThat(user.getEmail()).isEqualTo(email);
  }

  @Test
  @Order(3)
  public void 사용자_이메일_조회_미존재() {
    User user = userRepository.findByEmail("empty@email.com").orElse(null);

    assertThat(user).isNull();
  }

}
