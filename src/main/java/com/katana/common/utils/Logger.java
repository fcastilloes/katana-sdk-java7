package com.katana.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

/**
 * Created by juan on 30/09/16.
 */
public class Logger {

    public static final int INFO = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;

    public static final int DEBUG = 4;
    private static boolean isActive;

    private Logger() {
        // private constructor to block the instantiation of this object
    }

    public static void activate() {
        isActive = true;
    }

    public static void deactivate() {
        isActive = false;
    }

    public static void log(int type, String message) {
        if (isActive) {
            logToStdout(getLog(type, message));
        }
    }

    public static void log(Exception e) {
        log(Logger.ERROR, e.getMessage());
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            log(Logger.ERROR, stackTraceElement.toString());
        }
    }

    private static String getLog(int type, String message) {
        SimpleDateFormat standardDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = Calendar.getInstance().getTime();

        String time = standardDateFormat.format(date);
        return time + " [" + getType(type) + "] [SDK] " + message;
    }

    private static String getType(int type) {
        switch (type) {
            case INFO:
                return "INFO";
            case WARNING:
                return "WARNING";
            case ERROR:
                return "ERROR";
            case DEBUG:
                return "DEBUG";
            default:
                return "INFO";
        }
    }

    private static void logToStdout(String message) {
        java.util.logging.Logger.getGlobal().log(Level.INFO, message);
        System.out.println(message);
    }
}
