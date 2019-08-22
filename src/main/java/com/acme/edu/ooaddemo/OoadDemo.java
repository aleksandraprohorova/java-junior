package com.acme.edu.ooaddemo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class OoadDemo {
    public static void main(String[] args) {
        //FM:
        Arrays.asList();
        Collections.emptyList();
        Optional.of(0);
    }
}

class Logger {
    //Factory Method [GoF]
    private LoggerFilter filter = XmlConfigFilterFactory.create(5);
    private LoggerSaver saver = new ConsoleLoggerSaver();

    public void log(String message, int severityLevel) {
        if (!filter.isFiltered(message, severityLevel)) {
            saver.save(message);
        }
    }
}

class SeverityLevelLoggerFilter implements LoggerFilter {
    private final int MAX_ALLOWED_SEVERITY;

    SeverityLevelLoggerFilter(int max_allowed_severity) {
        MAX_ALLOWED_SEVERITY = max_allowed_severity;
    }

    @Override
    public boolean isFiltered(String message, int severityLevel) {
        return severityLevel < MAX_ALLOWED_SEVERITY;
    }
}

class ConsoleLoggerSaver implements LoggerSaver {
    @Override
    public void save(String message) {
        System.out.println(message);
    }
}