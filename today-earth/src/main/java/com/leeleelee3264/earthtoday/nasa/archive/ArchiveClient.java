package com.leeleelee3264.earthtoday.nasa.archive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class ArchiveClient {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${nasa.api.url}")
    private String apiUrl;

    @Value("${nasa.api.key}")
    private String apiKey;

    private final RestTemplateBuilder restTemplateBuilder;

    private final String subUrl = "/EPIC/archive/natural/";
    private final HttpHeaders headers;

    public ArchiveClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;

        this.headers = new HttpHeaders();
        this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
    }

    public byte[] get(LocalDate date, String imagePath) throws HttpClientErrorException{
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String url = apiUrl + subUrl + formattedDate + "/png/" + imagePath +  "?api_key=" + apiKey;

        try {
            ResponseEntity<byte[]> response = this.restTemplateBuilder.build().exchange(url,
                    HttpMethod.GET,
                    new HttpEntity<>(null, this.headers),
                    byte[].class
            );

            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
            throw new HttpClientErrorException(e.getStatusCode(), "Failed to download earth image.");
        }
    }

}
