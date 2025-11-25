package loggerUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {
    // trebuie sa definim o instanta de logger
    // trebuie sa definesc o metoda care sa porneasca un test
    // opreasca un test
    // metoda info, motada error

    private static final Logger logger = LogManager.getLogger("loggerUtility");
    public static void startTestCase (String testName) {
        logger.info("****** Execution Started" + testName + "******");
    }
    public static void endTestCase (String testName) {
        logger.info("****** Execution Ended" + testName + "******");
    }
    public static void infoTest (String message) {
        logger.info(message);
    }
    public static void errorTest (String message) {
        logger.error(message);
    }
}
