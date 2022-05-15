package com.github.minfaatong.hello.consumer;

import com.github.minfaatong.hello.service.HelloWorldService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class HelloWorldConsumerTest {
    @Mock
    private HelloWorldService helloService;
    HelloWorldConsumer sut;

    @Before
    public void setup() {
        initMocks(this);
        sut = new HelloWorldConsumer(helloService);
    }

    @Test
    public void test_sayHello_Ok() {
        final String firstName = "firstname";
        final String lastName = "lastname";

        when(helloService.sayHello(anyString())).thenReturn("hello firstname lastname");

        final String actual = sut.sayHello(firstName, lastName);

        verify(helloService, times(1)).sayHello(anyString());
        assertEquals("hello firstname lastname", actual);
    }

    @Test
    public void test_sayHello_emptyString() {
        when(helloService.sayHello(anyString())).thenReturn("hello anonymous");

        final String actual = sut.sayHello("", "");

        verify(helloService, never()).sayHello(anyString());
        assertEquals("hello anonymous", actual);
    }
}
