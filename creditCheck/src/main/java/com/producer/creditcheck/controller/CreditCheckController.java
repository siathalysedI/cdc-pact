package com.producer.creditcheck.controller;

import com.producer.creditcheck.model.CreditCheckRequest;
import com.producer.creditcheck.model.CreditCheckResponse;
import com.producer.creditcheck.service.CreditCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCheckController {

    @Autowired
    private CreditCheckService creditCheckService;

    public CreditCheckController() {}

    @PostMapping(value = "/credit-scores", produces = "application/json", consumes = "application/json")
    public CreditCheckResponse doCreditCheck(@RequestBody CreditCheckRequest creditCheckRequest) {
        return creditCheckService.doCheck(creditCheckRequest);

    }
}