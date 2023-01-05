package com.leeleelee3264.earthtoday.nasa.client;

import com.leeleelee3264.earthtoday.util.LoggingUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.v1.StatusUpdate;
import twitter4j.v1.UploadedMedia;

import javax.annotation.PostConstruct;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
                .httpConnectionTimeout(30000)
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
     * 이미지나 GIF 아무튼 멀티미디어를 이용해서 트윗을 하는 것을 별도의 이름의 메소드를 만들지 않고 오버라이딩을 하자.
     * TODO: 그런데 이제 이렇게 하면.. 오버로딩을 할 수 없다. 인자를 FileType으로 바꿔야할듯
     * @param message
     * @param filePath
     */
    public void tweets(String filePath) {
        File imageFile = new File(filePath);

        try {
            // Get empty StatusUpdate object to get new StatusUpdate object with media id.
            StatusUpdate statusUpdate = StatusUpdate.of("");
            UploadedMedia media = null;

            if (imageFile.length() > 1_000_000) {
                media = this.twitterInstance.v1().tweets().uploadMediaChunked(imageFile.getName(), new BufferedInputStream(new FileInputStream(imageFile)));
            } else {
                media = this.twitterInstance.v1().tweets().uploadMedia(imageFile);
            }

            if (media != null) {
                StatusUpdate update2 = statusUpdate.mediaIds(media.getMediaId());
                this.twitterInstance.v1().tweets().updateStatus(update2);
            }

        } catch (TwitterException | FileNotFoundException e) {
            LoggingUtils.error(e);
        }
    }

}
