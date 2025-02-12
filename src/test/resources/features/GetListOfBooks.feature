Feature: Fetch all books from the system

  Scenario: User retrieves all books from the system
    Given the books api is available and operational
    When the user sends a "GET" request to the "/books" endpoint
    Then the response status code should be 200
    And the response header "Content-Type" should be "application/json; charset=utf-8"
#    And the response body should contain a list of books
#    And each book should have a name type and availability
    And the response should have the following books information
      | bookId | bookName              | bookType    | bookAvailability |
      | 1      | The Russian           | fiction     | true             |
      | 2      | Just as I Am          | non-fiction | false            |
      | 3      | The Vanishing Half    | fiction     | true             |
      | 4      | The Midnight Library  | fiction     | true             |
      | 5      | Untamed               | non-fiction | true             |
      | 6      | Viscount Who Loved Me | fiction     | true             |