package com.leeleelee3264.earthtoday;

import com.leeleelee3264.earthtoday.nasa.service.EarthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import twitter4j.TwitterException;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class TodayEarthApplication {

    private static final Logger log = LoggerFactory.getLogger(TodayEarthApplication.class);

    public static void main(String[] args) throws TwitterException {
        ConfigurableApplicationContext context = SpringApplication.run(TodayEarthApplication.class, args);

        LocalDate date = LocalDate.now().minusDays(2);

        EarthService service = context.getBean(EarthService.class);

        service.saveImages(date);
        service.tweetEarth(date);

        log.info("finish");
        log.info("My name is: {}", "Jamie");

    }

}
