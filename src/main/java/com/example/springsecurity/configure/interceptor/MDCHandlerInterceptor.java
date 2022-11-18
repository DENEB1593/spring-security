package com.example.springsecurity.configure.interceptor;

import ch.qos.logback.classic.ClassicConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MDCHandlerInterceptor implements HandlerInterceptor {

  private final static Logger log = LoggerFactory.getLogger(MDCHandlerInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request,
                          HttpServletResponse response,
                          Object handler) throws Exception {

    log.info("ingress in interceptor");

    MDC.put(ClassicConstants.REQUEST_X_FORWARDED_FOR, request.getHeader("X-Forwarded-For"));
    MDC.put(ClassicConstants.REQUEST_USER_AGENT_MDC_KEY, request.getHeader("User-Agent"));
    MDC.put(ClassicConstants.REQUEST_REQUEST_URI, request.getRequestURI());

    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler,
                             Exception ex) throws Exception {
    MDC.clear();
  }
}
