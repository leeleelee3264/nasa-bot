package com.leeleelee3264.earthtoday.nasa.archive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class ArchiveClient {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${nasa.api.url}")
    private String apiUrl;

    @Value("${nasa.api.key}")
    private String apiKey;

    private final String subUrl = "/EPIC/archive/natural/";

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public ArchiveClient() {
        restTemplate = new RestTemplate();

        headers = new HttpHeaders();

    }
}
