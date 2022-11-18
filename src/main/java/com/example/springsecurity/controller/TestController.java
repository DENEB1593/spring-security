package com.example.springsecurity.controller;

import com.example.springsecurity.configure.interceptor.MDCHandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

  private final static Logger log = LoggerFactory.getLogger(TestController.class);

  @GetMapping(path = "get")
  public void get() {
    log.info("TestController - get");
  }

  @PostMapping(path = "post")
  public void post() {
    log.info("TestController - post");
  }
}
