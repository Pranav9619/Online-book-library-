package com.obs.util;

import java.util.logging.Logger;

public class AppLog {
    static Logger logger = Logger.getLogger(AppLog.class.getName());

    public void info(String message) { logger.info(message); }
}
