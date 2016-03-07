Feature: Clear Logs

  @NotReady
  Scenario: Clear logs
    Given a logger
    And something has been logged to the logger
    And a matcher that will match the thing that was logged
    When the clear logs rule is invoked
    Then the matcher will not match
