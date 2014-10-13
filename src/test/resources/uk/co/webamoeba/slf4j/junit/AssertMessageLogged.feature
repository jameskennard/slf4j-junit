Feature: Assert message logged

Scenario Outline: String message
Given a logger
And a String message "Some Message" logged at "<level>" level
And a loggedInfo matcher with the String message "Some Message"
When I match the logger
Then it will match
Examples:
| level |
| info  |

Scenario Outline: Formatted message
Given a logger
And a Formatted message "Format {}" with argument "Argument" logged at info level
And a loggedInfo matcher with the Formatted message "Format {}" with argument "Argument"
When I match the logger
Then it will match
Examples:
| level |
| info  |

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

Scenario Outline: Message not logged
Given a logger named "<loggerName>"
And a loggedInfo matcher with the String message "<logMessage>"
When I match the logger
Then it will not match
And the mismatch description is:
	"""
	info to "<loggerName>" with message "<logMessage>" was not logged 
	"""
Examples:
| loggerName | logMessage   |
| SomeLogger | Some Message |

@NotReady
Scenario: Message logged at different level

@NotReady
Scenario: Message logged against different logger
