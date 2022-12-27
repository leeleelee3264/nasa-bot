package com.leeleelee3264.earthtoday;

import com.leeleelee3264.earthtoday.hello.GreetingClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TodayEarthApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TodayEarthApplication.class, args);

        GreetingClient greetingClient = context.getBean(GreetingClient.class);
        System.out.println(">> message = " + greetingClient.getMessage().block());

        // Test cal
    }

}
