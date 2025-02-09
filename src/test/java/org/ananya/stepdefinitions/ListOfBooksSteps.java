package org.ananya.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class ListOfBooksSteps {

    //Member variable
    private static final String BASE_URL = "https://simple-books-api.glitch.me";
    RequestSpecification request;
    Response response;

    @Given("the books api is available and operational")
    public void theBooksApiIsAvailableAndOperational() {
        request = RestAssured.given();
        request.baseUri(BASE_URL);
    }

    @When("the user sends a {string} request to the {string} endpoint")
    public void theUserSendsARequestToTheEndpoint(String requestType, String endPoint) {
        response = request.when().get(endPoint);
        System.out.println(response.getBody());
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedResponseCode) {
        int actualResponseCode = response.statusCode();
        assertEquals(actualResponseCode, expectedResponseCode);
    }

    @Then("the response body should contain a list of books")
    public void the_response_body_should_contain_a_list_of_books() {
        List<Integer> actualIdList = response.jsonPath().getList("id");
        int expectedNumberOfBooks = 6;
        int actualNumberOfBooks = actualIdList.size();
        assertEquals(actualNumberOfBooks, expectedNumberOfBooks, "Actual NUmber of books not matching with expected number of books.");
    }

    @And("each book should have a name type and availability")
    public void eachBookShouldHaveANameTypeAndAvailability() {
        List<String> actualNameList = response.jsonPath().getList("name");
        List<String> actualTypeList = response.jsonPath().getList("type");
        List<Boolean> actualAvailableList = response.jsonPath().getList("available");

        assertEquals(actualNameList.size(), 6, "Book names missing");
        assertEquals(actualTypeList.size(), 6, "Book types missing");
        assertEquals(actualAvailableList.size(), 6, "Book availabilities missing");
    }

    @And("the response header {string} should be {string}")
    public void theResponseHeaderShouldBe(String headerName, String expectedHeaderValue) {
        String actualHeaderValue = response.getHeader(headerName);
//        System.out.println(response.getContentType());
//        Assert.assertEquals(actualHeaderValue, expectedHeaderValue);
        assertEquals(actualHeaderValue, expectedHeaderValue, "Headers not matching.");
    }

    @And("the response should have the following books information")
    public void theResponseShouldHaveTheFollowingBooksInformation(DataTable dataTable) {
        List<Map<String, String>> expectedBooks = dataTable.asMaps();
        int expectedBookCount = expectedBooks.size();

        SoftAssert softAssert = new SoftAssert();

        //Run loop for expectedBookCount times
        for (int i = 0; i < expectedBookCount; i++) {
            Map<String, String> eachExpectedBook = expectedBooks.get(i);

            //Store expected values in variables
            int expectedBookId = Integer.parseInt(eachExpectedBook.get("bookId"));
            String expectedBookName = eachExpectedBook.get("bookName");
            String expectedBookType = eachExpectedBook.get("bookType");
            boolean expectedBookAvailability = Boolean.parseBoolean(eachExpectedBook.get("bookAvailability"));

            //Store actual values in variables
            int actualBookId = response.jsonPath().getInt("[" + i +"].id");
            String actualBookName = response.jsonPath().get("[" + i +"].name");
            String actualBookType = response.jsonPath().get("[" + i +"].type");
            boolean actualBookAvailability = response.jsonPath().getBoolean("[" + i +"].available");


            //Compare actual vs expected
            softAssert.assertEquals(actualBookId,expectedBookId, "Book Ids not matching");
            softAssert.assertEquals(actualBookName,expectedBookName, "Book names not matching");
            softAssert.assertEquals(expectedBookType,actualBookType, "Book types not matching");
            softAssert.assertEquals(expectedBookAvailability,actualBookAvailability, "Book availability not matching ");
        }

        softAssert.assertAll();
    }
}
