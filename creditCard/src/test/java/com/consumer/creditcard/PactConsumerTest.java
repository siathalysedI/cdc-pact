package com.consumer.creditcard;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.MockServerConfig;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import com.consumer.creditcard.controller.CreditCardController;
import com.consumer.creditcard.model.CreditCardResponse;
import com.consumer.creditcard.model.CreditCheck;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "creditCheck")
@PactDirectory("src/test/resources/contracts")
public class PactConsumerTest {

    @BeforeAll
    public static void config() {
        System.setProperty("pact.writer.overwrite", "true");
    }

    private CreditCheck creditCheck;

    @Pact(consumer = "creditCard")
    public RequestResponsePact pact_getCreditCheckResponse(PactDslWithProvider builder) {
        //headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        //req body
        PactDslJsonBody bodyReq = new PactDslJsonBody()
                .integerType("citizenNumber", 1);
        //res body
        PactDslJsonBody bodyRes = new PactDslJsonBody()
                .stringType("score", "HIGH");

        return builder.given("citizen exist")
                .uponReceiving("POST req to credit check provider")
                .path("/credit-scores")
                .method(RequestMethod.POST.name())
                .body(bodyReq)
                .headers(headers)
                .willRespondWith()
                .headers(headers)
                .status(200)
                .body(bodyRes)
                .toPact();
    }


    @Test
    @PactTestFor(pactMethod = "pact_getCreditCheckResponse")
    @MockServerConfig(hostInterface = "localhost", port = "1234")
    public void testApplyCreditCard(MockServer mockServer) throws JsonProcessingException, JSONException {
        CreditCardController creditCardController = new CreditCardController();
        creditCardController.setBaseURL(mockServer.getUrl());

        creditCheck = new CreditCheck();
        creditCheck.setCardType("Standard");
        creditCheck.setCitizenNumber(1);

        String expectedJson = "{\n" +
                "\"status\": \"GRANTED\"\n" +
                "}";

        CreditCardResponse response = creditCardController.applyCC(creditCheck);

        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(response);
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT_ORDER);
    }
}
