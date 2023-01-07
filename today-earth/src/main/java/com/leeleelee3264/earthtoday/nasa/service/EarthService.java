package com.leeleelee3264.earthtoday.nasa.service;

import com.leeleelee3264.earthtoday.nasa.client.ArchiveClient;
import com.leeleelee3264.earthtoday.nasa.client.MetaClient;
import com.leeleelee3264.earthtoday.nasa.client.TwitterClient;
import com.leeleelee3264.earthtoday.nasa.dto.Meta;
import com.leeleelee3264.earthtoday.util.LoggingUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
public class EarthService {

    @Value("${earth.image.directory}")
    private String imageDirectory;

    @Value("${earth.image.gif}")
    private String gifName;

    private MetaClient metaClient;
    private ArchiveClient archiveClient;
    private TwitterClient twitterClient;


    public  EarthService(MetaClient metaClient, ArchiveClient archiveClient, TwitterClient twitterClient) {
        this.metaClient = metaClient;
        this.archiveClient = archiveClient;
        this.twitterClient = twitterClient;
    }

    public void tweetGif(LocalDate date) {
        String fullGifName = this.imageDirectory + "/" + date.toString() + gifName;
        File gifFile = new File(fullGifName);

        this.twitterClient.tweet_media(gifFile);
    }

    public void tweetMsg() {
        this.twitterClient.tweet("Hello World in method");
    }


    public void saveImages(LocalDate date) {

        String dirName = imageDirectory + "/" + date.toString();

        try {
            this.makeDirectory(dirName);
        } catch (IOException e) {
            LoggingUtils.error(e);
            return;
        }

        try {
            List<Meta> nasaPaths = this.metaClient.get(date);
            for (Meta meta: nasaPaths) {
                byte[] fByte = this.archiveClient.get(date, meta.getImagePath());
                this.saveImage(dirName, meta.getImagePath(), fByte);
            }

            LoggingUtils.info("Successfully download images");
        } catch (HttpClientErrorException e) {
            LoggingUtils.error(e);
        }
    }

    private void saveImage(String dirName, String imageName, byte[] fByte) {
        String fullName = dirName + "/" + imageName;

        try {
            Files.write(Paths.get(fullName), fByte);
        } catch (IOException e) {
            LoggingUtils.error(e, "Failed to download Image: " + fullName);
        }
    }

    private void makeDirectory(String dirName) throws IOException {
        Files.createDirectories(Paths.get(dirName));
    }


}
