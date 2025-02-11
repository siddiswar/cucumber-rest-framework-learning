package org.ananya.stepdefinitions;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import io.restassured.specification.RequestSpecification;
import org.ananya.state.SharedContext;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class SharedSteps {


    //Member variable
    private static final String BASE_URL = "https://simple-books-api.glitch.me";

    @Inject
    SharedContext sharedContext;

    public static RequestSpecification request;

    @Given("the books api is available and operational")
    public void theBooksApiIsAvailableAndOperational() {
        sharedContext.getRequest().filter(new RequestLoggingFilter());
        sharedContext.getRequest().filter(new ResponseLoggingFilter());
        sharedContext.getRequest().baseUri(BASE_URL);
    }

    @When("the user sends a {string} request to the {string} endpoint")
    public void theUserSendsARequestToTheEndpoint(String requestType, String endPoint) {
        sharedContext.setResponse(sharedContext.getRequest().when().get(endPoint));
        System.out.println(sharedContext.getResponse().getBody());
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedResponseCode) {
        int actualResponseCode = sharedContext.getResponse().statusCode();
        assertEquals(actualResponseCode, expectedResponseCode);
    }

    @Then("the response body should contain a list of books")
    public void the_response_body_should_contain_a_list_of_books() {
        List<Integer> actualIdList = sharedContext.getResponse().jsonPath().getList("id");
        int expectedNumberOfBooks = 6;
        int actualNumberOfBooks = actualIdList.size();
        assertEquals(actualNumberOfBooks, expectedNumberOfBooks, "Actual NUmber of books not matching with expected number of books.");
    }

    @And("each book should have a name type and availability")
    public void eachBookShouldHaveANameTypeAndAvailability() {
        List<String> actualNameList = sharedContext.getResponse().jsonPath().getList("name");
        List<String> actualTypeList = sharedContext.getResponse().jsonPath().getList("type");
        List<Boolean> actualAvailableList = sharedContext.getResponse().jsonPath().getList("available");

        assertEquals(actualNameList.size(), 6, "Book names missing");
        assertEquals(actualTypeList.size(), 6, "Book types missing");
        assertEquals(actualAvailableList.size(), 6, "Book availabilities missing");
    }

    @And("the response header {string} should be {string}")
    public void theResponseHeaderShouldBe(String headerName, String expectedHeaderValue) {
        String actualHeaderValue = sharedContext.getResponse().getHeader(headerName);
//        System.out.println(response.getContentType());
//        Assert.assertEquals(actualHeaderValue, expectedHeaderValue);
        assertEquals(actualHeaderValue, expectedHeaderValue, "Headers not matching.");
    }

}
