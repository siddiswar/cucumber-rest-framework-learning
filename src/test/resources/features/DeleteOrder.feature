Feature: Delete Order API

  @skipped
  Scenario: User deletes existing order
    Given the books api is available and operational
    And the user is authorized
    When the user sends a "DELETE" request to the "/orders/meqz9qwsm-WPXqIzgKMef" endpoint
    Then the response status code should be 204