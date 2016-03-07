Feature: Logger by Name

  Scenario Outline: Logger by name
    Given the name "<loggerName>"
    When I call logger with the name
    Then the logger is named "<loggerName>"
    And I can match logging events against the logger

    Examples: 
      | loggerName    |
      | Some Log Name |
