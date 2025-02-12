Feature: Create Order

  Scenario: order a book
    Given the books api is available and operational
    And the user is authorized
    When the user wants to order the book with id 1
    And the user sends a "POST" request to the "/orders" endpoint
    Then the response status code should be 201
    And the response header "Content-Type" should be "application/json; charset=utf-8"
    And the response should have the newly created order id



