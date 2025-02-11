package org.ananya.state;

import io.cucumber.guice.ScenarioScoped;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@ScenarioScoped
public class SharedContext {

    RequestSpecification request = RestAssured.given();
    Response response;

    public RequestSpecification getRequest() {
        return request;
    }

    public void setRequest(RequestSpecification request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
