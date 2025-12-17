package org.example.logFormatter;

import org.example.logMessage.LogMessage;

public class DetailedLogFormatter implements LogFormmater {
  @Override
  public String format(LogMessage logMessage) {
    String detailedLog = String.format("Detailed Log: logType: %s, message: %s, time %s", logMessage.getLogType(),logMessage.getMessage(), logMessage.getLocalDateTime().toString() );
    return detailedLog;
  }
  
}
