package com.consumer.creditcard.controller;

import com.consumer.creditcard.model.CreditCardResponse;
import com.consumer.creditcard.model.CreditCheck;
import com.consumer.creditcard.model.CreditCheckRequest;
import com.consumer.creditcard.model.CreditCheckResponse;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CreditCardController {

    private String baseURL = "http://localhost:9000";

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }


    @PostMapping("/credit-card")
    public CreditCardResponse applyCC(@RequestBody CreditCheck creditCheck) {
        int citizenNumber = creditCheck.getCitizenNumber();
        CreditCheckResponse creditCheckResponse = getCreditCheckResponse(citizenNumber);

         if (creditCheckResponse.getScore().equals(CreditCheckResponse.Score.HIGH))
            return new CreditCardResponse(CreditCardResponse.Status.GRANTED);
        else if (creditCheckResponse.getScore().equals(CreditCheckResponse.Score.LOW))
            return new CreditCardResponse(CreditCardResponse.Status.REJECTED);

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @Nullable
    private CreditCheckResponse getCreditCheckResponse(int citizenNumber) {
        RestTemplate restTemplate = new RestTemplate();
        CreditCheckResponse creditCheckResponse = restTemplate.postForObject(baseURL + "/credit-scores", new CreditCheckRequest(citizenNumber),
                CreditCheckResponse.class);
        return creditCheckResponse;
    }
}
