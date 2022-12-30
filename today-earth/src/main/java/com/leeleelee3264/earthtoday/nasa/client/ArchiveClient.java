package com.leeleelee3264.earthtoday.nasa.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Component
public class ArchiveClient {
    @Value("${nasa.api.url}")
    private String apiUrl;

    @Value("${nasa.api.key}")
    private String apiKey;

    private RestTemplateBuilder restTemplateBuilder;

    private final String subUrl = "/EPIC/archive/natural/";
    private final HttpHeaders headers;

    public ArchiveClient() {
        this.restTemplateBuilder = new RestTemplateBuilder();

        this.headers = new HttpHeaders();
        this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
    }

    public byte[] get(LocalDate date, String imagePath){
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String url = apiUrl + subUrl + formattedDate + "/png/" + imagePath +  "?api_key=" + apiKey;

        ResponseEntity<byte[]> response = this.restTemplateBuilder.build().exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(null, this.headers),
                byte[].class
        );

        return response.getBody();
    }

}
