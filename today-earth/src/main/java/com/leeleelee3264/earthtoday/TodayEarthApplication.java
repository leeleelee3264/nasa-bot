package com.leeleelee3264.earthtoday;

import com.leeleelee3264.earthtoday.util.LoggingUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.NumberFormat;

@SpringBootApplication
@EnableScheduling
public class TodayEarthApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodayEarthApplication.class, args);

        // for memory monitoring
        Runtime rT = Runtime.getRuntime();

        final NumberFormat format = NumberFormat.getInstance();
        final long maxMemory = rT.maxMemory();
        final long allocatedMemory = rT.totalMemory();
        final long freeMemory = rT.freeMemory();

        final long mb = 1024 * 1024;
        final String mega = " MB";

        LoggingUtils.info("========================== Memory Info ==========================");
        LoggingUtils.info("Free memory: " + format.format(freeMemory / mb) + mega);
        LoggingUtils.info("Allocated memory: " + format.format(allocatedMemory / mb) + mega);
        LoggingUtils.info("Max memory: " + format.format(maxMemory / mb) + mega);
        LoggingUtils.info("Total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / mb) + mega);
        LoggingUtils.info("=================================================================\n");

    }

}
