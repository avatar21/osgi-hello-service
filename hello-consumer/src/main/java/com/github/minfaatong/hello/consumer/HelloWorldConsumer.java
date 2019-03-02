package com.github.minfaatong.hello.consumer;

import com.github.minfaatong.hello.service.HelloWorldService;
import com.google.inject.Inject;

public class HelloWorldConsumer {
  private final HelloWorldService helloService;

  @Inject
  public HelloWorldConsumer(HelloWorldService helloService) {
    this.helloService = helloService;
  }
  
  public void sayHello(final String firstName, final String lastName) {
    helloService.sayHello(String.format("%s %s", firstName, lastName));
  }
}