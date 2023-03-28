package com.consumer.creditcard.model;

import lombok.Data;

@Data
public class CreditCheckResponse {
    private Score score;
    private String error;

//    public Score getScore() {
//        return score;
//    }
//
//    public void setScore(Score score) {
//        this.score = score;
//    }

    public enum Score {
        HIGH, LOW
    }
}