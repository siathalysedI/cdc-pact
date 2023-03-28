package com.producer.creditcheck.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCheckResponse {
    private Score score;

    public enum Score {
        HIGH, LOW
    }
}