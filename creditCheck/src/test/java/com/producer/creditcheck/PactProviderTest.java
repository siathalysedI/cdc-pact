package com.producer.creditcheck;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.StateChangeAction;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("creditCheck")
//@PactFolder("src/main/java/pacts") //pact on local repo
//@PactBroker(url = "https://rajatmishra.pactflow.io/",
//        authentication = @PactBrokerAuth(token = "ev2_6hqwATAlBVoEW-SqJw"))
//@PactBroker(url = "http://localhost:9292")
@PactBroker(url = "http://pact-broker:9292") // -- for build
public class PactProviderTest {

    @LocalServerPort
    public int port;
    static String BRANCH_NAME, GIT_COMMIT_ID;

    @BeforeAll
    static void enablePublishingPact() {
        try {
            BRANCH_NAME = GitBranchName.getCurrentGitBranch();
            GIT_COMMIT_ID = GitBranchName.getCurrentGitCommit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.setProperty("pact.verifier.publishResults", "true");

        /*comment for local run*/
        if (BRANCH_NAME.contains("remotes/origin/")) {
            BRANCH_NAME = BRANCH_NAME.replace("remotes/origin/", "");
        }
        System.setProperty("pact.provider.version", GIT_COMMIT_ID);
        System.setProperty("pact.provider.branch", BRANCH_NAME);
        System.setProperty("pact.provider.tag", GIT_COMMIT_ID);
    }

    @BeforeEach
    public void setup(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port)
        );
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void testApplyCreditCard(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State(value = "citizen exist", action = StateChangeAction.SETUP)
    public void citizenExist() {
        //setup data

    }

    @State(value = "citizen exist", action = StateChangeAction.TEARDOWN)
    public void deleteCitizen() {
//delete data

    }


}
