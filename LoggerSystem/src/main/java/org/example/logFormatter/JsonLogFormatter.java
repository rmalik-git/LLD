package org.example.logFormatter;

import org.example.logMessage.LogMessage;
import org.json.JSONObject;

public class JsonLogFormatter implements LogFormmater {

  @Override
  public String format(LogMessage logMessage) {
    JSONObject jsonlog = new JSONObject();
    jsonlog.put("logType", logMessage.getLogType());
    jsonlog.put("message", logMessage.getMessage());
    jsonlog.put("time", logMessage.getLocalDateTime().toString());
    return jsonlog.toString();
  }
}
