Feature: Assert logged with Marker

  Scenario Outline: Logged with Marker and String Message
    Given a logger
    And a marker
    And a String message "Some Message" with the marker logged at "<level>" level
    And a log matcher at "<level>" level with the marker and the String message "Some Message"
    When I match the logger
    Then it will match
      | level |
      | info  |
