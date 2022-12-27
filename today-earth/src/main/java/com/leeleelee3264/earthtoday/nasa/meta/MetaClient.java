package com.leeleelee3264.earthtoday.nasa.meta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public class MetaClient {

    @Value("${nasa.api.url}")
    private String apiUrl;

    @Value("${nasa.api.key}")
    private String apiKey;

    private final String subUrl = "/EPIC/api/natural/date/";

    private final WebClient client;

    public MetaClient(WebClient.Builder builder, LocalDate date) {
        String formattedDate = date.toString();
        this.client = builder.baseUrl(apiUrl).build();
    }

    public Mono<List<Meta>> get() {
        // TODO: 여기 리스트로 바꿔야 하는 게 아닐까?
        return this.client
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Meta>>() {});
    }
}
