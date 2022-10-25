package com.leeleelee3264.earthtoday.nasa;

import reactor.core.publisher.Mono;

public interface NasaClient<T> {

    Mono<T> get();
}
