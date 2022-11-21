package com.example.springsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

  private final static Logger log = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper om;

  private String email;

  private String password;

  private String birth;


  @BeforeAll
  void setUp() {
    email = "controller_test@email.com";
    password = "1234";
    birth = "20000817";
  }

  @Test
  @Order(1)
  @DisplayName("사용자_가입")
  void joinTest() throws Exception {
    JoinRequest request = new JoinRequest(email, password, birth);

    ResultActions resultActions = mockMvc.perform(
      post("/api/user/join")
        .contentType(APPLICATION_JSON)
        .content(om.writeValueAsString(request))
    );

    resultActions.andExpect(status().isCreated());

    String responseBody = resultActions
      .andReturn()
      .getResponse()
      .getContentAsString();

    UserDto response = om.readValue(responseBody, UserDto.class);

    SoftAssertions bundle = new SoftAssertions();
    bundle.assertThat(response).isNotNull();
    bundle.assertThat(response.getSeq()).isNotNull();
    bundle.assertThat(response.getBirth()).isEqualTo(birth);
    bundle.assertThat(response.getEmail()).isEqualTo(email);
    bundle.assertAll();
  }

  @Test
  @Order(2)
  @DisplayName("사용자_이메일_존재여부_존재시")
  void checkEmailExistTest() throws Exception {

    Map<String, String> request = singletonMap("email", email);

    ResultActions resultActions = mockMvc.perform(
      get("/api/user/exists")
        .contentType(APPLICATION_JSON)
        .content(om.writeValueAsString(request))
    );

    resultActions.andExpect(status().isOk());

    String responseString =resultActions.andReturn()
      .getResponse()
      .getContentAsString();

    Boolean response = om.readValue(responseString, Boolean.class);

    assertThat(response).isTrue();
  }

  @Test
  @Order(3)
  @DisplayName("사용자_이메일_존재여부_존재하지않음")
  void checkEmailExistIfNotExistTest() throws Exception {

    Map<String, String> request = singletonMap("email", "empty@email.com");

    ResultActions resultActions = mockMvc.perform(
      get("/api/user/exists")
        .contentType(APPLICATION_JSON)
        .content(om.writeValueAsString(request))
    );

    resultActions.andExpect(status().isOk());

    String responseString =resultActions.andReturn()
      .getResponse()
      .getContentAsString();

    Boolean response = om.readValue(responseString, Boolean.class);

    assertThat(response).isFalse();
  }

}