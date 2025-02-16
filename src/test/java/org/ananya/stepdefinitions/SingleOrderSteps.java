package org.ananya.stepdefinitions;

import com.google.inject.Inject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.ananya.domain.Order;
import org.ananya.state.SharedContext;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class SingleOrderSteps {

    @Inject
    SharedContext sharedContext;


    @And("the response should have the following single order information using pojo")
    public void theResponseShouldHaveTheFollowingSingleOrderInformationUsingPojo(DataTable dataTable) {

        //Step 1: Expected Order Details
        Map<String, String> expectedOrder = dataTable.asMaps().get(0);

        SoftAssert softAssert = new SoftAssert();
        String expectedOrderId = expectedOrder.get("orderId");
        int expectedBookId = Integer.parseInt(expectedOrder.get("bookId"));
        String expectedCustomerName = expectedOrder.get("customerName");
        String expectedCreatedBy = expectedOrder.get("createdBy");
        int expectedQunatity = Integer.parseInt(expectedOrder.get("quantity"));
        long expectedTimeStamp = Long.parseLong(expectedOrder.get("timestamp"));

        //Step 2: Actual Order Details

        //Deserialize api response to Order object
        Order actualOrder = sharedContext.getResponse().jsonPath().getObject("", Order.class);

        String actualOrderId = actualOrder.getId();
        int actualOrderBookId = actualOrder.getBookId();
        String actualOrderCustomerName = actualOrder.getCustomerName();
        String actualOrderCreatedBy = actualOrder.getCreatedBy();
        int actualOrderQuantity = actualOrder.getQuantity();
        long actualOrderTimestamp = actualOrder.getTimestamp();

        //Step 3: Compare Actual vs Expected
        softAssert.assertEquals(actualOrderId, expectedOrderId, "OrderId matching");
        softAssert.assertEquals(actualOrderBookId, expectedBookId, "BookId matching");
        softAssert.assertEquals(actualOrderCustomerName, expectedCustomerName, "CustomerName matching");
        softAssert.assertEquals(actualOrderCreatedBy, expectedCreatedBy, "CreatedBy matching");
        softAssert.assertEquals(actualOrderQuantity, expectedQunatity, "quantity matching");
        softAssert.assertEquals(actualOrderTimestamp, expectedTimeStamp, "timestamp matching");

        softAssert.assertAll();
    }
}
