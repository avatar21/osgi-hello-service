package com.github.minfaatong.hello.consumer;

import com.github.minfaatong.hello.service.HelloWorldService;
import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;

public class HelloWorldConsumer {
  private final HelloWorldService helloService;

  @Inject
  public HelloWorldConsumer(HelloWorldService helloService) {
    this.helloService = helloService;
  }
  
  public String sayHello(final String firstName, final String lastName) {
    if (StringUtils.isBlank(firstName) && StringUtils.isBlank(lastName)) {
      return "hello anonymous";
    }
    return helloService.sayHello(String.format("%s %s", firstName, lastName));
  }
}