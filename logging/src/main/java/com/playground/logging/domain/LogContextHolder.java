package com.playground.logging.domain;

public class LogContextHolder {

    private static final ThreadLocal<LogContext> threadData = new ThreadLocal<>();

    public static void clear() {
        threadData.remove();
    }

    public static LogContext get() {
        return threadData.get();
    }

    public static void set(LogContext context) {
        clear();
        threadData.set(context);
    }

}
