package com.leeleelee3264.earthtoday.nasa.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Meta {

    static final String IMAGE_PREFIX = "epic_1b_";
    static final String IMAGE_EXTENSION = ".png";

    private String identifier;
    public String getImagePath() {
        return IMAGE_PREFIX + identifier + IMAGE_EXTENSION;
    }
}
