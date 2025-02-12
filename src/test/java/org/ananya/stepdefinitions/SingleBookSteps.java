package org.ananya.stepdefinitions;

import com.google.inject.Inject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.ananya.domain.Book;
import org.ananya.state.SharedContext;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class SingleBookSteps {

    @Inject
    SharedContext sharedContext;

    @And("the response should have the following single book information")
    public void theResponseShouldHaveTheFollowingSingleBookInformation(DataTable dataTable) {
        Map<String, String> expectedBook = dataTable.asMaps().get(0);

        SoftAssert softAssert = new SoftAssert();

        //Store expected values in variables
        int expectedBookId = Integer.parseInt(expectedBook.get("bookId"));
        String expectedBookName = expectedBook.get("bookName");
        String expectedBookAuthor = expectedBook.get("bookAuthor");
        String expectedBookIsbn = expectedBook.get("bookIsbn");
        String expectedBookType = expectedBook.get("bookType");
        Double expectedBookPrice = Double.valueOf(expectedBook.get("bookPrice"));
        int expectedBookStock = Integer.parseInt(expectedBook.get("bookCurrentStock"));
        boolean expectedBookAvailability = Boolean.parseBoolean(expectedBook.get("bookAvailability"));

        //Store actual values in variables
        // Response is read using JsonPath

        int actualBookId = sharedContext.getResponse().jsonPath().getInt("id");
        String actualBookName = sharedContext.getResponse().jsonPath().get("name");
        String actualBookAuthor = sharedContext.getResponse().jsonPath().get("author");
        String actualBookIsbn = sharedContext.getResponse().jsonPath().get("isbn");
        String actualBookType = sharedContext.getResponse().jsonPath().get("type");
        double actualBookPrice = sharedContext.getResponse().jsonPath().getDouble("price");
        int actualBookStock = sharedContext.getResponse().jsonPath().getInt("current-stock");

        boolean actualBookAvailability = sharedContext.getResponse().jsonPath().getBoolean("available");

        //Compare actual vs expected
        softAssert.assertEquals(actualBookId, expectedBookId, "Book Id not matching");
        softAssert.assertEquals(actualBookName, expectedBookName, "Book name not matching");
        softAssert.assertEquals(actualBookAuthor, expectedBookAuthor, "Book author not matching");
        softAssert.assertEquals(actualBookIsbn, expectedBookIsbn, "Book isbn not matching");
        softAssert.assertEquals(expectedBookType, actualBookType, "Book type not matching");
        softAssert.assertEquals(expectedBookPrice, actualBookPrice, "Book price not matching");
        softAssert.assertEquals(expectedBookStock, actualBookStock, "Book stock not matching");
        softAssert.assertEquals(expectedBookAvailability, actualBookAvailability, "Book availability not matching ");

        softAssert.assertAll();
    }

    @And("the response should have the following single book information using pojo")
    public void theResponseShouldHaveTheFollowingSingleBookInformationUsingPojo(DataTable dataTable) {
        Map<String, String> expectedBook = dataTable.asMaps().get(0);

        SoftAssert softAssert = new SoftAssert();

        //Store expected values in variables
        int expectedBookId = Integer.parseInt(expectedBook.get("bookId"));
        String expectedBookName = expectedBook.get("bookName");
        String expectedBookAuthor = expectedBook.get("bookAuthor");
        String expectedBookIsbn = expectedBook.get("bookIsbn");
        String expectedBookType = expectedBook.get("bookType");
        Double expectedBookPrice = Double.valueOf(expectedBook.get("bookPrice"));
        int expectedBookStock = Integer.parseInt(expectedBook.get("bookCurrentStock"));
        boolean expectedBookAvailability = Boolean.parseBoolean(expectedBook.get("bookAvailability"));

        //Store actual values in variables
        // Response is deserialized into POJO (Java Object)
        // Joackson library converts (deserialization) Json string into pojo object
        Book actualBook = sharedContext.getResponse().as(Book.class);
        int actualBookId = actualBook.getId();
        String actualBookName = actualBook.getName();
        String actualBookAuthor = actualBook.getAuthor();
        String actualBookIsbn = actualBook.getIsbn();
        String actualBookType = actualBook.getType();
        double actualBookPrice = actualBook.getPrice();
        int actualBookStock = actualBook.getCurrentStock();

        boolean actualBookAvailability = sharedContext.getResponse().jsonPath().getBoolean("available");

        //Compare actual vs expected
        softAssert.assertEquals(actualBookId, expectedBookId, "Book Id not matching");
        softAssert.assertEquals(actualBookName, expectedBookName, "Book name not matching");
        softAssert.assertEquals(actualBookAuthor, expectedBookAuthor, "Book author not matching");
        softAssert.assertEquals(actualBookIsbn, expectedBookIsbn, "Book isbn not matching");
        softAssert.assertEquals(expectedBookType, actualBookType, "Book type not matching");
        softAssert.assertEquals(expectedBookPrice, actualBookPrice, "Book price not matching");
        softAssert.assertEquals(expectedBookStock, actualBookStock, "Book stock not matching");
        softAssert.assertEquals(expectedBookAvailability, actualBookAvailability, "Book availability not matching ");

        softAssert.assertAll();
    }
}
