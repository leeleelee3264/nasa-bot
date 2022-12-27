package com.leeleelee3264.earthtoday.nasa.meta;

import lombok.Getter;

@Getter
public class Meta {

    static final String IMAGE_PREFIX = "epic_1b_";
    static final String IMAGE_EXTENSION = ".png";

    private String identifier;
    private String imageName;

    private Meta(String identifier, String imageName) {
        this.identifier = identifier;
        this.imageName = imageName;
    }

    public Meta fromAPI(String identifier) {
        String imageName = IMAGE_PREFIX + identifier + IMAGE_EXTENSION;
        return new Meta(identifier, imageName);
    }
}
