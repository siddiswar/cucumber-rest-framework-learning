Feature:  Get single book order API

  Scenario Outline: User retrieves a single order
    Given the books api is available and operational
    And the user is authorized
    When the user sends a "GET" request to the "/orders/<orderId>" endpoint
    Then the response status code should be 200
    And the response header "Content-Type" should be "application/json; charset=utf-8"
    And the response should have the following single order information using pojo
      | orderId   | bookId   | customerName   | createdBy   | quantity   | timestamp   |
      | <orderId> | <bookId> | <customerName> | <createdBy> | <quantity> | <timestamp> |

    Examples:
      | orderId               | bookId | customerName | createdBy                                                        | quantity | timestamp     |
      | 1amCG21JUNNIQdcul0bKZ | 1      | lola 01      | f43c45113bcbfff7bc9bb5a007880e0a688df29ed0d8b14c0962ad35d03ea268 | 1        | 1739387638459 |

