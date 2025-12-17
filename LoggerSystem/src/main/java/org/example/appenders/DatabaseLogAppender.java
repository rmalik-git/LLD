package org.example.appenders;

import org.example.logFormatter.LogFormmater;
import org.example.logMessage.LogMessage;

public class DatabaseLogAppender implements Appender {
  
  private LogFormmater formatter;
  
  public DatabaseLogAppender(LogFormmater formatter) {
    this.formatter = formatter;
  }
  
  @Override
  public void append(LogMessage message) {
    String formattedMessage = formatter.format(message);
    System.out.println("Database Log: " + formattedMessage);
  }
  
  @Override
  public void setFormatter(LogFormmater formatter) {
    this.formatter = formatter;
  }
  
}
