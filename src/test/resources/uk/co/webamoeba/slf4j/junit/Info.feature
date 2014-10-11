Feature: Assert info logged

Scenario: Info Logged with String message
Given a logger
And a String message "Some Message" logged at info level
And a loggedInfo matcher with the String message "Some Message"
When I match the logger
Then it will match
