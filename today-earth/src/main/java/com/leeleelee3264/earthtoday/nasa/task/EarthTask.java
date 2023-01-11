package com.leeleelee3264.earthtoday.nasa.task;

import com.leeleelee3264.earthtoday.exception.BotException;
import com.leeleelee3264.earthtoday.nasa.service.EarthService;
import com.leeleelee3264.earthtoday.util.LoggingUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EarthTask {

    private final EarthService earthService;

    public EarthTask(EarthService earthService) {
        this.earthService = earthService;
    }

//    @Scheduled(cron = "0 59 23 * * *")
    @Scheduled(cron = "0 35 18 * * *")
    public void triggerEarthBot() {
        // 당일이나 전날 데이터를 조회하면 NASA에서 자료를 올려주지 않은 경우가 많아서 2일 정도 전의 데이터를 조회한다.
        LocalDate today = LocalDate.now();
        LoggingUtils.info("Task start, run EarthBot date: {}", today);

        LocalDate targetDate = today.minusDays(2);


        try {
            this.earthService.saveImages(targetDate);
            this.earthService.tweetEarth(targetDate);

            LoggingUtils.info("Successfully run EarthBot date: {}", today);
        } catch (BotException e) {
            LoggingUtils.error(e);
        }

    }
}
