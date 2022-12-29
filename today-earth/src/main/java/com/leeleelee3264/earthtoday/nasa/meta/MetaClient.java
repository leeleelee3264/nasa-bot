package com.leeleelee3264.earthtoday.nasa.meta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MetaClient {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${nasa.api.url}")
    private String apiUrl;

    @Value("${nasa.api.key}")
    private String apiKey;

    private final String subUrl = "/EPIC/api/natural/date/";

    private final RestTemplate restTemplate;

    private final HttpHeaders headers;


    public MetaClient() {
        restTemplate = new RestTemplate();

        headers = new HttpHeaders();
        headers.set("accept", "application/json");
    }

    public List<Meta> get(LocalDate date) {
        String url = apiUrl + subUrl + date.toString() + "?api_key=" + apiKey;

        // TODO: http 통신이 실패 했을 경우, 어떻게 에러 처리를 할까 https://hororolol.tistory.com/645
        try {
            ResponseEntity<List<Meta>> res = restTemplate.exchange(url,
                    HttpMethod.GET,
                    new HttpEntity<>(null, headers),
                    new ParameterizedTypeReference<List<Meta>>() {}
            );

            return res.getBody();
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }
}
