package com.spotlight.util;

/**
 * @author IliaNik on 30.03.2017.
 */
public enum SpotFields {
    ID("id"),
    TITLE("title"),
    RATING("rating"),
    INFO("info"),
    LOCATION("location");

    SpotFields(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }
}
