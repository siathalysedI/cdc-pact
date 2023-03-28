package com.producer.creditcheck.service;

import com.producer.creditcheck.model.CreditCheckRequest;
import com.producer.creditcheck.model.CreditCheckResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreditCheckService {
    public CreditCheckResponse doCheck(CreditCheckRequest creditCheckRequest) {
//        CreditCheckResponse creditCheckResponse = null;
        if (creditCheckRequest.getCitizenNumber() == 1)
            return new CreditCheckResponse(CreditCheckResponse.Score.HIGH);
        if (creditCheckRequest.getCitizenNumber() == 2)
            return new CreditCheckResponse(CreditCheckResponse.Score.LOW);
        else
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );

        //return null;
    }
}