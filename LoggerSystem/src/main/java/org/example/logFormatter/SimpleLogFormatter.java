package org.example.logFormatter;

import org.example.logMessage.LogMessage;

public class SimpleLogFormatter implements LogFormmater {
  @Override
  public String format(LogMessage message) {
    return "Simple Log: " + message.getMessage();
  }
  
}
