package com.consumer.creditcard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardResponse {

    private Status status;

//    public CreditCardResponse(Status status) {
//        this.status = status;
//    }

    public enum Status {
        GRANTED, REJECTED
    }
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
}
