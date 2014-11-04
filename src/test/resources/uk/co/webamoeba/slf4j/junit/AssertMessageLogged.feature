Feature: Assert logged with message

Background:
Given a logger

Scenario Outline: String message
Given a String message "Some Message" logged at "<level>" level
And a log matcher at "<level>" level with the String message "Some Message"
When I match the logger
Then it will match
Examples:
| level |
| info  |

Scenario Outline: Formatted message
Given a Formatted message "Format {}" with argument "Argument" logged at "<level>" level
And a log matcher at "<level>" level with the Formatted message "Format {}" with argument "Argument"
When I match the logger
Then it will match
Examples:
| level |
| info  |

Scenario Outline: Formatted message logged, String message matched
Given a Formatted message "Format {}" with argument "Argument" logged at "<level>" level
And a log matcher at "<level>" level with the String message "Format Argument"
When I match the logger
Then it will match
Examples:
| level |
| info  |

Scenario Outline: String message logged, Formatted message matched
Given a String message "Format Argument" logged at "<level>" level
And a log matcher at "<level>" level with the Formatted message "Format {}" with argument "Argument"
When I match the logger
Then it will match
Examples:
| level |
| info  |

Scenario Outline: Message not logged
Given a logger named "<loggerName>"
And a log matcher at "<level>" level with the String message "<logMessage>"
When I match the logger
Then it will not match
And the mismatch description is:
	"""
	info to "<loggerName>" with message "<logMessage>" was not logged 
	"""
Examples:
| loggerName | level | logMessage   |
| SomeLogger | info  | Some Message |

@NotReady
Scenario Outline: Message logged at different level
Given a logger named "<loggerName>"
And a log matcher at "<level>" level with the String message "Some Message"
And a String message "Some Message" logged at "<differentLevel>" level
When I match the logger
Then it will not match
| loggerName | level | differentLevel |
| SomeLogger | info  | warn           |

Scenario Outline: Message logged against different logger
Given a logger named "<loggerName>"
And a String message "Some Message" logged with "<differentLoggerName>" at "<level>" level
And a log matcher at "<level>" level with the String message "Some Message"
When I match the logger
Then it will not match
| loggerName | differentLoggerName | level |
| Some Logger| Some Other Logger   | info  |