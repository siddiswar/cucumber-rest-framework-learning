package org.ananya.stepdefinitions;

import com.google.inject.Inject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.ananya.domain.Book;
import org.ananya.state.SharedContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class GetAllBooksSteps {

    private static final Logger logger = LoggerFactory.getLogger(GetAllBooksSteps.class);

    @Inject
    SharedContext sharedContext;

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
            int actualBookId = sharedContext.getResponse().jsonPath().getInt("[" + i + "].id");
            String actualBookName = sharedContext.getResponse().jsonPath().get("[" + i + "].name");
            String actualBookType = sharedContext.getResponse().jsonPath().get("[" + i + "].type");
            boolean actualBookAvailability = sharedContext.getResponse().jsonPath().getBoolean("[" + i + "].available");

            //Compare actual vs expected
            softAssert.assertEquals(actualBookId, expectedBookId, "Book Ids not matching");
            softAssert.assertEquals(actualBookName, expectedBookName, "Book names not matching");
            softAssert.assertEquals(expectedBookType, actualBookType, "Book types not matching");
            softAssert.assertEquals(expectedBookAvailability, actualBookAvailability, "Book availability not matching ");
        }
        softAssert.assertAll();
    }

    @And("the response should have the following books information using pojo")
    public void theResponseShouldHaveTheFollowingBooksInformationUsingPojo(DataTable dataTable) {
        List<Map<String, String>> expectedBooks = dataTable.asMaps();
        List<Book> actualBooks = sharedContext.getResponse().jsonPath().getList("", Book.class);

        int expectedBookCount = expectedBooks.size();

        logger.info("Expected total books: {}", expectedBookCount);
        logger.info("Actual total books: {}", actualBooks.size());

        SoftAssert softAssert = new SoftAssert();

        //Run loop for expectedBookCount times
        for (int i = 0; i < expectedBookCount; i++) {
            Map<String, String> eachExpectedBook = expectedBooks.get(i);
            logger.info("Verifying the book with number : {}", i);

            logger.info("Expected book details : {}", eachExpectedBook);

            //Store expected values in variables
            int expectedBookId = Integer.parseInt(eachExpectedBook.get("bookId"));
            String expectedBookName = eachExpectedBook.get("bookName");
            String expectedBookType = eachExpectedBook.get("bookType");
            boolean expectedBookAvailability = Boolean.parseBoolean(eachExpectedBook.get("bookAvailability"));

            Book actualBook = actualBooks.get(i);
            logger.info("Actual book details : {}", actualBook.toString());

            //Store actual values in variables
            int actualBookId = actualBook.getId();
            String actualBookName = actualBook.getName();
            String actualBookType = actualBook.getType();
            boolean actualBookAvailability = actualBook.getAvailable();

            //Compare actual vs expected
            softAssert.assertEquals(actualBookId, expectedBookId, "Book Ids not matching");
            softAssert.assertEquals(actualBookName, expectedBookName, "Book names not matching");
            softAssert.assertEquals(expectedBookType, actualBookType, "Book types not matching");
            softAssert.assertEquals(expectedBookAvailability, actualBookAvailability, "Book availability not matching ");
        }
        softAssert.assertAll();
    }
}
