package com.leeleelee3264.earthtoday;

import com.leeleelee3264.earthtoday.nasa.archive.ArchiveClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

@SpringBootApplication
public class TodayEarthApplication {

    private static final Logger log = LoggerFactory.getLogger(TodayEarthApplication.class);

    private static String earthImageDirectory = "/Users/leelee/study/earth-resources";

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TodayEarthApplication.class, args);

        LocalDate date = LocalDate.now().minusDays(4);
        String dirName = earthImageDirectory + "/" + date.toString();

        try {
            Files.createDirectories(Paths.get(dirName));
        } catch (IOException e) {
            // TODO: log를 {} 써서 포메터로 예쁘게 뽑는 법이 있었다.
            log.error(e.getMessage());
        }

//        MetaClient metaClient = context.getBean(MetaClient.class);
//
//        List<Meta> m = metaClient.get(date);
//
//        for(Meta mm: m) {
//            System.out.println(mm.getImagePath());
//        }
//
//        System.out.println(m.size());

        // TODO 디렉터리 만드는 곳, 이 meta, archive를 부르는 api가 있어야 한다.
        String t = "https://api.nasa.gov/EPIC/archive/natural/2022/10/22/png/epic_1b_20221022004554.png?api_key=aumlN0C5xNVPnqlSHE3PVhHxEzrfm53Entwl6yhi";

        ArchiveClient archiveClient = context.getBean(ArchiveClient.class);
        byte[] fByte = archiveClient.get(date, t);

        try {
            Files.write(Paths.get(dirName + "/test.png"), fByte);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("finish");

    }

}
