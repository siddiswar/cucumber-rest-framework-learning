Feature: Fetch a book with give book id

#  TC_01 is Redundant because of TC_02
  @TC_01 @PositiveTest
  Scenario: User retrieves a single book using Id
    Given the books api is available and operational
    When the user sends a "GET" request to the "/books/1" endpoint
    Then the response status code should be 200
    And the response header "Content-Type" should be "application/json; charset=utf-8"
    And the response should have the following single book information using pojo
      | bookId | bookName    | bookAuthor                        | bookIsbn   | bookType | bookPrice | bookCurrentStock | bookAvailability |
      | 1      | The Russian | James Patterson and James O. Born | 1780899475 | fiction  | 12.98     | 12               | true             |

  @TC_02 @PositiveTest
  Scenario Outline: User retrieves a single book using Id using scenario outline
    Given the books api is available and operational
    When the user sends a "GET" request to the "/books/<bookId>" endpoint
    Then the response status code should be 200
    And the response header "Content-Type" should be "application/json; charset=utf-8"
#    And the response should have the following single book information  using json path
    And the response should have the following single book information using pojo
      | bookId   | bookName   | bookAuthor   | bookIsbn   | bookType   | bookPrice   | bookCurrentStock   | bookAvailability   |
      | <bookId> | <bookName> | <bookAuthor> | <bookIsbn> | <bookType> | <bookPrice> | <bookCurrentStock> | <bookAvailability> |

    Examples:
      | bookId | bookName           | bookAuthor                        | bookIsbn   | bookType | bookPrice | bookCurrentStock | bookAvailability |
      | 1      | The Russian        | James Patterson and James O. Born | 1780899475 | fiction  | 12.98     | 12               | true             |
      | 3      | The Vanishing Half | Brit Bennett                      |            | fiction  | 16.2      | 987              | true             |

 #  TC_03 is Redundant because of TC_05
  @TC_03 @NegativeTest
  Scenario: User tries to fetch using invalid book id
    Given the books api is available and operational
    When the user sends a "GET" request to the "/books/abcdefg" endpoint
    Then the response status code should be 404

     #  TC_04 is Redundant because of TC_05
  @TC_04 @NegativeTest
  Scenario: User tries to fetch using non existing book id
    Given the books api is available and operational
    When the user sends a "GET" request to the "/books/10" endpoint
    Then the response status code should be 404

  @TC_05 @NegativeTest
  Scenario Outline: User tries to fetch using invalid or non existing book ids
    Given the books api is available and operational
    When the user sends a "GET" request to the "/books/<bookId>" endpoint
    Then the response status code should be 404

    Examples:
      | bookId  |
      | abcdefg |
      | 10      |