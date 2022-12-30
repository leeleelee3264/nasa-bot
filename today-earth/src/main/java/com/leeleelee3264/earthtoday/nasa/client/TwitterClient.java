package com.leeleelee3264.earthtoday.nasa.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Component
public class TwitterClient {

    @Value("${twitter.consumer.key}")
    private String consumerKey;

    @Value("${twitter.consumer.secret}")
    private String consumerSecret;

    @Value("${twitter.access.token}")
    private String accessToken;

    @Value("${twitter.access.token.secret}")
    private String accessTokenSecret;

    private Twitter twitter;

    public TwitterClient() {
        this.twitter = Twitter.newBuilder()
                .oAuthConsumer(consumerKey, consumerSecret)
                .oAuthAccessToken(accessToken, accessTokenSecret)
                .build();
    }

    public void tweet(String message) throws TwitterException {
        this.twitter.v1().tweets().updateStatus(message);
    }

}
