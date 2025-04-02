package com.bharath.order_service;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.wiremock.integrations.testcontainers.WireMockContainer;

//@SpringbootTest is used to for integration testing
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
@AutoConfigureMockMvc
public abstract class AbstractIT {
    //this is a base class which is run before every test to reduce rewriting the code everytime
    //now we can use the RestAssured setup to invoke the api endpoints
    //how do we know on which random port it is using, we can use @localServerPort for that
    @LocalServerPort
    int port;

    //now we can bind the port to the URL address
    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
    //now we can use the RestAssured setup to invoke the api endpoints


    //this is used to mock the wiremock container to mock the api endpoints for testing
    static WireMockContainer wireMockServer = new WireMockContainer( "wiremock/wiremock:3.5.2-alpine");

    @BeforeAll
    static void beforeAll() {
        wireMockServer.start();
        WireMock.configureFor(wireMockServer.getHost(), wireMockServer.getFirstMappedPort());
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("orders.catalog-service-url", wireMockServer::getBaseUrl);
    }

    protected static void mockGetProductByCode(String code, String name, BigDecimal price) {
        stubFor(WireMock.get(urlMatching("/api/products/" + code))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)
                        .withBody(
                    """
                    {
                        "code": "%s",
                        "name": "%s",
                        "price": %f
                    }
                """
                                        .formatted(code, name, price.doubleValue()))));
    }

}
