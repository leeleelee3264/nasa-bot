package com.leeleelee3264.earthtoday.nasa.archive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.datetime.joda.DateTimeFormatterFactoryBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ArchiveClient {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${nasa.api.url}")
    private String apiUrl;

    @Value("${nasa.api.key}")
    private String apiKey;

    private final String subUrl = "/EPIC/archive/natural/";

    private final RestTemplate restTemplate;

    public ArchiveClient() {
        this.restTemplate = new RestTemplate();
    }

    public void get(LocalDate date, String url) {
//        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//        String url = apiUrl + subUrl + formattedDate + "/png/" + imagePath +  "?api_key=" + apiKey;

        File file = restTemplate.execute(url, HttpMethod.GET, null, clientHttpResponse -> {
            File ret = File.createTempFile("download", "tmp");
            StreamUtils.copy(clientHttpResponse.getBody(), Files.newOutputStream(ret.toPath()));
            return ret;
        });

    }
}
