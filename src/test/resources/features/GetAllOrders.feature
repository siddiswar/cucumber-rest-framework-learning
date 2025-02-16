Feature: Get All book Orders API

  Scenario: User retrieves all orders
    Given the books api is available and operational
    And the user is authorized
    When the user sends a "GET" request to the "/orders" endpoint
    Then the response status code should be 200
    And the response header "Content-Type" should be "application/json; charset=utf-8"
    And the response should have the all Orders information using pojo
      | id                    | bookId | customerName                    | createdBy                                                        | quantity | timestamp     |
      | 1amCG21JUNNIQdcul0bKZ | 1      | lola 01                         | f43c45113bcbfff7bc9bb5a007880e0a688df29ed0d8b14c0962ad35d03ea268 | 1        | 1739387638459 |
      | V3IAL3U3S2HYYtW7YmUu5 | 1      | Customer name using java object | f43c45113bcbfff7bc9bb5a007880e0a688df29ed0d8b14c0962ad35d03ea268 | 1        | 1739443572588 |
      | L5IPTUFgi9AwqZ_4cdn6U | 1      | some customer                   | f43c45113bcbfff7bc9bb5a007880e0a688df29ed0d8b14c0962ad35d03ea268 | 1        | 1739641677796 |
