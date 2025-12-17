package org.example.appenders;

import org.example.logFormatter.LogFormmater;
import org.example.logMessage.LogMessage;

public class ConsoleLogAppender implements Appender {
  
  private LogFormmater formatter;
  
  public ConsoleLogAppender(LogFormmater formatter) {
    this.formatter = formatter;
  }
  
  @Override
  public void append(LogMessage message) {
    String formattedMessage = formatter.format(message);
    System.out.println(formattedMessage);
  }
  
  @Override
  public void setFormatter(LogFormmater formatter) {
    this.formatter = formatter;
  }
  
}
