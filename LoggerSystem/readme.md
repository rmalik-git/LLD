functional Requirement:
* MULTIPLE LOG LEVEL
  1. SHOULD SUPPORT DIFFERENT loggine levels (e.g., DEBUG, INFO, WARNING, ERROR, CRITICAL).
  2. each log level has a priority INFO < DEBUG < WARNING < ERROR < CRITICAL
  3. Logger should filter based on confgured log level

* Multiple Output destination
  FILE, CONSOLE, DB

* Log Formatting
  Simple, Detailed, json format

* Core Logging Operation
  debug, info, warning, error, critical, generic(loglevel, message)

*  Logger Configuration
  setGlobal log level at runTime
  change logFOrmat at runtime
  change appender at Runtime/ enable disable appender at runtime

*  Logger Instance Management
   * Support Singleton Logger

Non functional Requirement:
 * Thread Safety: Logger should be thread-safe for concurrent use
Performance: Logging should not significantly impact application performance
Extensibility: Easy to add new appenders or formatters
Simplicity: Clean API for developers to use



-------------

entities: LOGGER, LogLEVEL,  APPENDERS, LOG_FORMATTER, LOGMESSAGE
