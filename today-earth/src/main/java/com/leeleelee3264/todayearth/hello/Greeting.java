package com.leeleelee3264.todayearth.hello;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Greeting {

    private String message;

    public Greeting() {}

    public Greeting(String message) {
        this.message = message;
    }
}
