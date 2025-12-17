package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.appenders.Appender;
import org.example.logFormatter.LogFormmater;
import org.example.logMessage.LogMessage;
import org.example.logMessage.LogType;

public class Logger {
  private static Logger logger;

  private LogType globalLogType;
  private List<Appender> appenders;
  private List<LogMessage> logMessages;

  private Logger() {
    this.globalLogType = LogType.INFO;
    this.appenders =  new ArrayList<>();
    logMessages = new ArrayList<>();
  }

  public static synchronized Logger getLogger() {
    if (logger == null) {
      logger = new Logger();
    }
    return logger;
  }

  public LogType getGlobalLogType() {
    return this.globalLogType;
  }


  public List<Appender> getAppenders() {
    return this.appenders;
  }

  public void setGlobalLogType(LogType globalLogType) {
    this.globalLogType = globalLogType;
  }

  public void addAppender(Appender appender) {
    if(!this.appenders.contains(appender)) {
      this.appenders.add(appender);
    }
  }

  public void removeAppender(Appender appender) {
    this.appenders.remove(appender);
  }

  public void debug(String message) {
    logMessage(message, LogType.DEBUG);
  }
  
  public void info(String message) {
    logMessage(message, LogType.INFO);
  }

  public void error(String message) {
   logMessage(message, LogType.ERROR);
  }

  public void critical(String message) {
   logMessage(message, LogType.CRITICAL);
  }

  public void warning(String message) {
   logMessage(message, LogType.WARNING);
  }

  public void logMessage(String message, LogType logType) {
    if(logType.ordinal() < this.globalLogType.ordinal()) {
      System.out.println("Log type "+logType+" is lower than global log type "+this.globalLogType+". Skipping log.");
      return;
    }
  
    LogMessage logMessage = new LogMessage(message,logType);
    for (Appender appender : this.appenders) {
      appender.append(logMessage);
    }
  }

}
