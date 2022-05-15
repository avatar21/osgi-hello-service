package com.github.minfaatong.hello.service.impl;

import com.github.minfaatong.hello.service.HelloWorldService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldServiceImpl implements HelloWorldService {
  private static final Logger logger = LoggerFactory.getLogger(HelloWorldServiceImpl.class);

  public String sayHello(final String who) {
    if (StringUtils.isBlank(who)) {
      return "hello anonymous";
    }
    final String message = String.format("hello %s", who);
    logger.info(message);
    return message;
  }
}