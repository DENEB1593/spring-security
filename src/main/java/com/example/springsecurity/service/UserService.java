package com.example.springsecurity.service;

import com.example.springsecurity.controller.JoinRequest;
import com.example.springsecurity.controller.UpdateRequest;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

  private final static Logger LOG = LoggerFactory.getLogger(UserService.class);

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Transactional(readOnly = true)
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public User join(JoinRequest request) {
    User user = new User(request);
    return userRepository.insert(user);
  }

  public void update(UpdateRequest request) {
    User user = new User(request);
    userRepository.update(user);
  }


}
