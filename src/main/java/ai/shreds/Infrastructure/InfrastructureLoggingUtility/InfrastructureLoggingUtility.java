package ai.shreds.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Utility class for logging across the application.
 * Provides methods to log error and info level messages.
 */
@Component
public class InfrastructureLoggingUtility {

    private final Logger logger = LoggerFactory.getLogger(InfrastructureLoggingUtility.class);
    private String loggingLevel;

    @Value("${app.logging.level}")
    public InfrastructureLoggingUtility(String loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    /**
     * Logs error messages.
     * @param message The error message to log.
     */
    public void logError(String message) {
        logger.error("Error in " + Thread.currentThread().getStackTrace()[2].getClassName() + ": " + message);
    }

    /**
     * Logs informational messages.
     * @param message The info message to log.
     */
    public void logInfo(String message) {
        logger.info("Info from " + Thread.currentThread().getStackTrace()[2].getClassName() + ": " + message);
    }
}