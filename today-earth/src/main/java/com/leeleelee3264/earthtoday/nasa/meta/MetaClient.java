package com.leeleelee3264.earthtoday.nasa.meta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class MetaClient {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${nasa.api.url}")
    private String apiUrl;

    @Value("${nasa.api.key}")
    private String apiKey;

    private final String subUrl = "/EPIC/api/natural/date/";

    private final RestTemplateBuilder restTemplateBuilder;

    private final HttpHeaders headers;


    public MetaClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;

        this.headers = new HttpHeaders();
        this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    public List<Meta> get(LocalDate date) throws HttpClientErrorException {
        String url = apiUrl + subUrl + date.toString() + "?api_key=" + apiKey;

        // TODO: http 통신이 실패 했을 경우, 어떻게 에러 처리를 할까 https://hororolol.tistory.com/645
        try {
            ResponseEntity<List<Meta>> res = this.restTemplateBuilder.build().exchange(url,
                    HttpMethod.GET,
                    new HttpEntity<>(null, this.headers),
                    new ParameterizedTypeReference<List<Meta>>() {}
            );

            return res.getBody();
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
            throw  new HttpClientErrorException(e.getStatusCode(), "Failed to load meta data for earth image.");
        }
    }
}
