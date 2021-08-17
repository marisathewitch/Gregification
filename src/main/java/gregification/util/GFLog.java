package gregification.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GFLog {

    public static Logger baseLogger;
    public static Logger exNihiloLogger;
    public static Logger ticonLogger;

    public static void init(Logger modLogger) {
        baseLogger = modLogger;
        exNihiloLogger = getLogger("Ex Nihilo");
        ticonLogger = getLogger("Tinker's Construct");
    }

    private static Logger getLogger(String name) {
        return LogManager.getLogger(String.format("%s: %s", baseLogger.getName(), name));
    }
}
