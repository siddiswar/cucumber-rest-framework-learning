package org.ananya.stepdefinitions;

import com.google.inject.Inject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.ananya.domain.Order;
import org.ananya.state.SharedContext;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class GetAllOrdersSteps {
    @Inject
    SharedContext sharedContext;

    @And("the response should have the all Orders information using pojo")
    public void theResponseShouldHaveTheAllOrdersInformationUsingPojo(DataTable dataTable) {

        //Step 1: Get all expected orders
        List<Map<String, String>> expectedOrders = dataTable.asMaps();

        //Step 2: Get all actual orders from api response
        //Deserializing the list of orders from API response to List<Order> pojos
        List<Order> actualOrders = sharedContext.getResponse().jsonPath().getList("", Order.class);

        int expectedOrdersList = expectedOrders.size();

        SoftAssert softAssert = new SoftAssert();

        //STep 3: Run loop for each expected order
        for (int i = 0; i < expectedOrdersList; i++) {

            //get the i'th expected order
            Map<String, String> eachExpectedOrder = expectedOrders.get(i);

            String expectedId = eachExpectedOrder.get("id");
            int expectedBookId = Integer.parseInt(eachExpectedOrder.get("bookId"));
            String expectedCustomerName = eachExpectedOrder.get("customerName");
            String expectedCreatedBy = eachExpectedOrder.get("createdBy");
            int expectedQuantity = Integer.parseInt(eachExpectedOrder.get("quantity"));
            long expectedTimeStamp = Long.parseLong(eachExpectedOrder.get("timestamp"));

            //get the i'th actual order
            Order actualOrder = actualOrders.get(i);

            String actualId = actualOrder.getId();
            int actualBookId = actualOrder.getBookId();
            String actualCustomerName = actualOrder.getCustomerName();
            String actualCreatedBy = actualOrder.getCreatedBy();
            int actualQuantity = actualOrder.getQuantity();
            long actualTimeStamp = actualOrder.getTimestamp();

            // compare actual vs expected
            softAssert.assertEquals(actualId, expectedId, "Ids not matching");
            softAssert.assertEquals(actualBookId, expectedBookId, "BookId not matching");
            softAssert.assertEquals(actualCustomerName, expectedCustomerName, "CustomerName not matching");
            softAssert.assertEquals(actualCreatedBy, expectedCreatedBy, "CreatedBy not matching ");
            softAssert.assertEquals(actualQuantity, expectedQuantity, "Quantity not matching");
            softAssert.assertEquals(actualTimeStamp, expectedTimeStamp, "TimeStamp not matching");
        }
        softAssert.assertAll();
    }


}
