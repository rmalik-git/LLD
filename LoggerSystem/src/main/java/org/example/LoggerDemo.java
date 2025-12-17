package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.appenders.Appender;
import org.example.appenders.ConsoleLogAppender;
import org.example.appenders.DatabaseLogAppender;
import org.example.appenders.FileLogAppender;
import org.example.logFormatter.DetailedLogFormatter;
import org.example.logFormatter.JsonLogFormatter;
import org.example.logFormatter.SimpleLogFormatter;
import org.example.logMessage.LogMessage;
import org.example.logMessage.LogType;

public class LoggerDemo {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger();
        logger.addAppender(new ConsoleLogAppender(new DetailedLogFormatter()));
         logger.addAppender(new FileLogAppender(new JsonLogFormatter()));
        

        logger.debug("This is a debug message.");
        logger.info("This is an info message.");
        logger.warning("This is a warning message.");
        logger.error("This is an error message.");
        logger.critical("This is a critical message.");

        
       
        System.out.println("\n-------Changing global log type to DEBUG..");
        logger.setGlobalLogType(LogType.DEBUG);
        logger.info("This is an info message.");


        logger.addAppender(new DatabaseLogAppender(new SimpleLogFormatter()));
        logger.debug("Another debug message after adding DatabaseLogAppender.");

    }

    private static void printLogMessages(List<LogMessage> logMessages) {
        for (LogMessage logMessage : logMessages) {
            System.out.printf("Log message %s, type %s:%n",logMessage.getMessage(), logMessage.getLogType());
        }
    }
}