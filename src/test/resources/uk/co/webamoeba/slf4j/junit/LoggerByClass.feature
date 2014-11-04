Feature: Logger by Class

  Scenario Outline: Logger by class
    Given the class "<class>"
    When I call logger with the class
    Then the logger is named "<class>"
    And I can match logging events against the logger

    Examples: 
      | class            |
      | java.lang.Object |
