package org.example.logFormatter;

import org.example.logMessage.LogMessage;

public interface LogFormmater {
  String format(LogMessage message);
}
