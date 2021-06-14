package com.sparta.adrian.view;

import com.sparta.adrian.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logging {
    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void traceLog(String message) {
        logger.trace(message);
    }

    public static void errorLog(String message) {
        logger.error(message);
    }
}
