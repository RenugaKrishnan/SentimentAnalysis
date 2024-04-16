package com.cc.sentimentanalysis.models;

public enum SentimentType {

    VERY_NEGATIVE(0), NEGATIVE(1), NEUTRAL(2), POSITIVE(3), VERY_POSITIVE(4);

    int score;

    private SentimentType(int score) {
        this.score = score;
    }

    public static SentimentType fromValue(int value) {
        for (SentimentType type: values()) {
            if (type.score == value) {
                return type;
            }
        }
        return SentimentType.NEUTRAL;
    }
}
