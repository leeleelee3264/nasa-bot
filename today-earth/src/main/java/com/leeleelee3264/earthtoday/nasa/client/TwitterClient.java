package com.leeleelee3264.earthtoday.nasa.client;

import com.leeleelee3264.earthtoday.util.LoggingUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.annotation.PostConstruct;

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

    private Twitter twitterInstance;


    @PostConstruct
    void init() {
        this.twitterInstance = Twitter.newBuilder()
                .oAuthConsumer(consumerKey, consumerSecret)
                .oAuthAccessToken(accessToken, accessTokenSecret)
                .build();
    }

    public void tweets(String message) {
        try {
            this.twitterInstance.v1().tweets().updateStatus(message);
        } catch (TwitterException e) {
            LoggingUtils.error(e);
        }
    }

    /**
     * 이미지나 GIF 아무튼 멀티미디어를 이용해서 트윗을 하는 것을 별도의 이름의 메소드를 만들지 않고
     * 오버라이딩을 하자.
     * @param message
     * @param GIF
     */
    public void tweets(String message, String GIF) {

    }

}
