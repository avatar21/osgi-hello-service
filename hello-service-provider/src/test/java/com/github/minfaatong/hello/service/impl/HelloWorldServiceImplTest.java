package com.github.minfaatong.hello.service.impl;

import com.github.minfaatong.hello.service.HelloWorldService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldServiceImplTest {
    HelloWorldService sut;

    @Before
    public void setup() {
        this.sut = new HelloWorldServiceImpl();
    }

    @Test
    public void test_hello_Ok() {
        final String name = "Ali";

        final String actual = sut.sayHello(name);

        assertEquals("hello Ali", actual);
    }

    @Test
    public void test_hello_anonymous() {
        final String name = "";

        final String actual = sut.sayHello(name);

        assertEquals("hello anonymous", actual);
    }
}
