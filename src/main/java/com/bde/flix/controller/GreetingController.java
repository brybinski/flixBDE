package com.bde.flix.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.bde.flix.controller.Payload.Greeting;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController
{
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/greeting")

    public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name)
    {
        return new Greeting(
                counter.incrementAndGet(),
                String.format(template, name));
    }
}