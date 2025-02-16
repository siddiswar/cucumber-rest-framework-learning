Feature: Get All book Orders API

  Scenario: User retrieves all orders
    Given the books api is available and operational
    And the user is authorized
    When the user sends a "GET" request to the "/orders" endpoint
    Then the response status code should be 200
    And the response header "Content-Type" should be "application/json; charset=utf-8"
    And the response should have the all Orders information using pojo
      | id                    | bookId | customerName | createdBy                                                        | quantity | timestamp     |
      | ygeieY2-XxkiFGEMh9Lzw | 1      | lola 01      | 092088cf9d61289278cc07b63a79ac1c56d8a67a7bc2367cd52ad61e9d2821c7 | 1        | 1739709762392 |
      | yD5P8EZ4WLScZNSxJcnG1 | 3      | lola 03      | 092088cf9d61289278cc07b63a79ac1c56d8a67a7bc2367cd52ad61e9d2821c7 | 1        | 1739709779157 |
      | miR2saC3DbaTkMxZy7MOZ | 5      | lola 05      | 092088cf9d61289278cc07b63a79ac1c56d8a67a7bc2367cd52ad61e9d2821c7 | 1        | 1739709787227 |
