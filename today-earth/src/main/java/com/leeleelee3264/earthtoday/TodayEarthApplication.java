package com.leeleelee3264.earthtoday;

import com.leeleelee3264.earthtoday.hello.GreetingClient;
import com.leeleelee3264.earthtoday.nasa.meta.Meta;
import com.leeleelee3264.earthtoday.nasa.meta.MetaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class TodayEarthApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TodayEarthApplication.class, args);


        MetaClient metaClient = context.getBean(MetaClient.class);
        List<Meta> m = metaClient.get(LocalDate.now().minusDays(3));

        for(Meta mm: m) {
            System.out.println(mm.getImagePath());
        }

        System.out.println(m.size());


    }

}
