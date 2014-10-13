Feature: Logger

Scenario Outline: Logger by name
Given the name "<loggerName>"
When I call logger with the logger name
Then the logger is named "<loggerName>"
Examples:
| loggerName    |
| Some Log Name |

Scenario Outline: Logger by class
Given the class "<class>"
When I call logger with the class
Then the logger is named "<class>"
Examples:
| class            |
| java.lang.Object | 