package ai.shreds.infrastructure;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.PostConstruct;

/**
 * Holds the configuration details for the database connection.
 */
@Getter
@Setter
@Configuration
public class InfrastructureDatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureDatabaseConfig.class);

    @Value("${databaseUrl}")
    private String databaseUrl;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @PostConstruct
    private void validateConfig() {
        logger.info("Starting configuration validation.");
        if (databaseUrl == null || databaseUrl.isEmpty() || username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new DatabaseConfigurationException("Invalid database configuration: Ensure all fields are provided.");
        }
        logger.info("Configuration validation completed successfully.");
    }
}

/**
 * Custom exception for handling database configuration errors.
 */
class DatabaseConfigurationException extends RuntimeException {
    public DatabaseConfigurationException(String message) {
        super(message);
    }
}