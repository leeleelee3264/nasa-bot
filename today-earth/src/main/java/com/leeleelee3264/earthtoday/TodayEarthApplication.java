package com.leeleelee3264.earthtoday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import twitter4j.TwitterException;

@SpringBootApplication
@EnableScheduling
public class TodayEarthApplication {

    public static void main(String[] args) throws TwitterException {
        SpringApplication.run(TodayEarthApplication.class, args);
    }

}
