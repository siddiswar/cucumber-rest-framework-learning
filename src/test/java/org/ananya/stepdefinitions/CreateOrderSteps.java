package org.ananya.stepdefinitions;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.ananya.domain.CreateOrderRequestBody;
import org.ananya.domain.CreateOrderResponseBody;
import org.ananya.state.SharedContext;
import org.testng.Assert;

public class CreateOrderSteps {

    @Inject
    SharedContext sharedContext;

    @When("the user wants to order the book with id {int}")
    public void theUserWantsToOrderTheBookWithId(int bookId) {
        sharedContext.getRequest().header("Content-Type","application/json");

       //Technique 1. Setting Request body using  string
//        String jsonBodyStr = "{\n" +
//                "    \"bookId\": 1,\n" +
//                "    \"customerName\":\"some customer4\",\n" +
//                "    \"quantity\":5\n" +
//                "}";
        //sharedContext.getRequest().body(jsonBodyStr);

        //Technique 2. Setting Request body using file
//        File file = new File("features/create-order.json");
//        sharedContext.getRequest().body(file);


        //Technique 3: Setting Request body using java object . Jackson library serializes POJO object into json string
        CreateOrderRequestBody createOrderRequestBody = new CreateOrderRequestBody();
        createOrderRequestBody.setBookId(bookId);
        createOrderRequestBody.setCustomerName("Customer name using java object ");
        createOrderRequestBody.setQuantity(3);

        sharedContext.getRequest().body(createOrderRequestBody);


    }

    @And("the response should have the newly created order id")
    public void theResponseShouldHaveTheNewlyCreatedOrderId() {
        //Response is read using JsonPath
        String actualOrderId = sharedContext.getResponse().jsonPath().get("orderId");
        boolean actualIsCreated = sharedContext.getResponse().jsonPath().getBoolean("created");
        Assert.assertNotNull(actualOrderId,"Order ID is null");
        Assert.assertTrue(actualIsCreated);

        //Response is deserialized into POJO (Java Object)
        CreateOrderResponseBody responseBody = sharedContext.getResponse().as(CreateOrderResponseBody.class);
        Assert.assertTrue(responseBody.getCreated());
        Assert.assertNotNull(responseBody.getOrderId());


    }
}
