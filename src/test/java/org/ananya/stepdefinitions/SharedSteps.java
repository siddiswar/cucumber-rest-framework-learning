package org.ananya.stepdefinitions;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import org.ananya.config.PropertiesConfig;
import org.ananya.state.SharedContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public class SharedSteps {

    //Member variables
    private static final Logger logger = LoggerFactory.getLogger(SharedSteps.class);

    @Inject
    SharedContext sharedContext;

    @Given("the books api is available and operational")
    public void theBooksApiIsAvailableAndOperational() {
        sharedContext.getRequest().filter(new RequestLoggingFilter());
        sharedContext.getRequest().filter(new ResponseLoggingFilter());
        String baseUrl = PropertiesConfig.getProperty("base_url");
        logger.info("Base URL : {}", baseUrl);
        sharedContext.getRequest().baseUri(baseUrl);
    }

    @And("the user is authorized")
    public void theUserIsAuthorized() {
        String bearerToken = PropertiesConfig.getProperty("bearer_token");
        logger.info("Bearer Token : {}", bearerToken);
        sharedContext.getRequest().header("Authorization", bearerToken);
    }

    @When("the user sends a {string} request to the {string} endpoint")
    public void theUserSendsARequestToTheEndpoint(String requestType, String endPoint) {
        logger.info("Request Type : {}", requestType);
        logger.info("Endpoint : {}", endPoint);

        switch (requestType) {
            case "GET":
                sharedContext.setResponse(sharedContext.getRequest().when().get(endPoint));
                break;
            case "POST":
                sharedContext.setResponse(sharedContext.getRequest().when().post(endPoint));
                break;
            case "PUT":
                sharedContext.setResponse(sharedContext.getRequest().when().put(endPoint));
                break;
            case "PATCH":
                sharedContext.setResponse(sharedContext.getRequest().when().patch(endPoint));
                break;
            case "DELETE":
                sharedContext.setResponse(sharedContext.getRequest().when().delete(endPoint));
                break;
            default:
                throw new IllegalArgumentException("Invalid request type : " + requestType);

        }
        logger.info(sharedContext.getResponse().asString());
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedResponseCode) {
        int actualResponseCode = sharedContext.getResponse().statusCode();
        logger.info("Response Code : {}", actualResponseCode);
        assertEquals(actualResponseCode, expectedResponseCode);
    }

    @And("the response header {string} should be {string}")
    public void theResponseHeaderShouldBe(String headerName, String expectedHeaderValue) {
        String actualHeaderValue = sharedContext.getResponse().getHeader(headerName);
        logger.info("Response Header value : {}", actualHeaderValue);
        Assert.assertEquals(actualHeaderValue, expectedHeaderValue, "Headers not matching.");
    }
}
