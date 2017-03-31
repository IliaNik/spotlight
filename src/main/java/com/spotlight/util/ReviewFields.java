package com.spotlight.util;

/**
 * @author IliaNik on 31.03.2017.
 */
public enum ReviewFields {
    ID("id"),
    TITLE("title"),
    TEXT("text"),
    RATING("rating"),
    DATE("date"),
    SPOT_ID("spotId"),
    USER_ID("userId");


    ReviewFields(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }
}
