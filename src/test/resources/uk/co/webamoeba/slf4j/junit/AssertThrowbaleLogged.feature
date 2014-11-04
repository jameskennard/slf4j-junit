Feature: Assert throwable logged

  Scenario Outline: String message
    Given a logger
    And a throwable
    And a the throwable is logged at "<level>" level with the String message "Some Message"
    And a log matcher at "<level>" level with the throwable and the String message "Some Message"
    When I match the logger
    Then it will match

    Examples: 
      | level |
      | info  |
