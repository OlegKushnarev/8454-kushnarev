package ru.focusstart.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FormatOutput extends Formatter {

    @Override
    public String format(LogRecord logRecord) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        return dateFormat.format(date) + " " + logRecord.getThreadID() + " " + logRecord.getMessage();
    }
}