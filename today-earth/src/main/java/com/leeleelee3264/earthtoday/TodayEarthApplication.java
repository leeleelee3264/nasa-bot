package com.leeleelee3264.earthtoday;

import com.leeleelee3264.earthtoday.nasa.service.EarthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class TodayEarthApplication {

    private static final Logger log = LoggerFactory.getLogger(TodayEarthApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TodayEarthApplication.class, args);

        LocalDate date = LocalDate.now().minusDays(4);

        EarthService service = context.getBean(EarthService.class);

        service.saveImages(date);
        log.info("finish");

    }

}
