Feature: Assert info logged

Scenario: String message
Given a logger
And a String message "Some Message" logged at info level
And a loggedInfo matcher with the String message "Some Message"
When I match the logger
Then it will match

Scenario: Formatted message
Given a logger
And a Formatted message "Format {}" with argument "Argument" logged at info level
And a loggedInfo matcher with the Formatted message "Format {}" with argument "Argument"
When I match the logger
Then it will match

Scenario: Formatted message logged, String message matched
Given a logger
And a Formatted message "Format {}" with argument "Argument" logged at info level
And a loggedInfo matcher with the String message "Format Argument"
When I match the logger
Then it will match

Scenario: String message logged, Formatted message matched
Given a logger
And a String message "Format Argument" logged at info level
And a loggedInfo matcher with the Formatted message "Format {}" with argument "Argument"
When I match the logger
Then it will match

Scenario: Message not logged
Given a logger
And a loggedInfo matcher with the String message "Some Message"
When I match the logger
Then it will not match