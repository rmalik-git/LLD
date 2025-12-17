package org.example.appenders;

import org.example.logFormatter.LogFormmater;
import org.example.logMessage.LogMessage;

public interface Appender {
  void append(LogMessage logMessage);
  void setFormatter(LogFormmater formatter);
}
