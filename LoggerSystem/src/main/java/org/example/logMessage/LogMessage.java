package org.example.logMessage;

import java.time.LocalDateTime;
import java.util.UUID;

public class LogMessage {
  private final String id;
  private final String message;
  private final LogType logType;
  private final LocalDateTime localDateTime;

  public LogMessage( String message, LogType logType) {
    this.id = UUID.randomUUID().toString();
    this.message = message;
    this.logType = logType;
    this.localDateTime = LocalDateTime.now();
  }

  public String getId() {
    return id;
  }
  public String getMessage() {
    return message;
  }
  public LogType getLogType() {
    return logType;
  }
  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return super.toString();
  }
}