package com.example.springsecurity.repository;

import com.example.springsecurity.controller.JoinRequest;
import com.example.springsecurity.model.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
  @DisplayName("사용자_전체_조회")
  public void findAllTest() {
    List<User> result = userRepository.findAll();
    assertThat(result).hasSizeGreaterThan(0);
  }

  @Test
  @Order(2)
  @DisplayName("사용자_추가")
  public void joinTest() {
    JoinRequest request = new JoinRequest(email, password, birth);
    User user = new User(request);

    User saved = userRepository.insert(user);

    assertThat(user).usingRecursiveComparison()
      .ignoringActualNullFields()
      .isEqualTo(saved);
  }

  @Test
  @Order(3)
  @DisplayName("사용자_이메일_조회")
  public void findByEmailTest() {
    User user = userRepository.findByEmail(email).orElse(null);

    assertThat(user).isNotNull();
    assertThat(user.getEmail()).isEqualTo(email);
  }

  @Test
  @Order(4)
  @DisplayName("사용자_이메일_조회(미존재)")
  public void findByEmailIfNotExists() {
    User user = userRepository.findByEmail("empty@email.com").orElse(null);

    assertThat(user).isNull();
  }

  @Test
  @Order(5)
  @DisplayName("사용자_정보_수정")
  public void updateTest() {
    User given = userRepository.findByEmail(email).orElse(null);

    assertThat(given).isNotNull();

    String modifyPassword = "1111";
    String modifyBirth = "19990101";

    User modified = new User.Builder(given)
      .password(modifyPassword)
      .birth(modifyBirth)
      .build();

    userRepository.update(modified);

    User updated = userRepository.findByEmail(email).orElse(null);

    SoftAssertions bundle = new SoftAssertions();
    bundle.assertThat(modifyPassword).isEqualTo(updated.getPassword());
    bundle.assertThat(modifyBirth).isEqualTo(updated.getBirth());
    bundle.assertThat(updated.getUpdatedAt()).isAfter(given.getUpdatedAt());
    bundle.assertAll();

  }


}
