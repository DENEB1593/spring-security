package com.example.springsecurity.controller;

import com.example.springsecurity.model.User;
import com.example.springsecurity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/user")
public class UserController {

  private final static Logger log = LoggerFactory.getLogger(UserController.class);

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  // 사용자 조회 by 이메일
  @GetMapping(path = "exists")
  public Boolean checkExists(@RequestBody Map<String, String> request) {
    String email = request.get("email");
    return userService.findByEmail(email).isPresent();
  }

  // 가입
  @ResponseStatus(CREATED)
  @PostMapping(path = "join")
  public UserDto join(@RequestBody JoinRequest request) {
    User user = userService.join(request);
    return new UserDto(user);
  }

}
