package com.bharath.catalog_service;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

//we use @SpringBootTest for integration tests,while testing web environment gives us random port from available ports
//so that no conflicts may arise when other services are deployed on other ports
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
public abstract class AbstractIT {

    //this is a base class which is run before every test to reduce rewriting the code everytime

    //how do we know on which random port it is using, we can use @localServerPort for that
    @LocalServerPort
    private int port;

    //now we can bind the port to the URL address
    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }
    //now we can use the RestAssured setup to invoke the api endpoints
}
