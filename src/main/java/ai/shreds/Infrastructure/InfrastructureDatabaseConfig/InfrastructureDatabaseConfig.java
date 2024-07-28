package ai.shreds.infrastructure; 
  
 import lombok.Getter; 
 import lombok.Setter; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 /** 
  * Holds the configuration details for the database connection. 
  */ 
 @Getter 
 @Setter 
 public class InfrastructureDatabaseConfig { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureDatabaseConfig.class); 
  
     private String databaseUrl; 
     private String username; 
     private String password; 
  
     /** 
      * Constructor for InfrastructureDatabaseConfig. 
      * @param databaseUrl the URL of the database 
      * @param username the username for the database access 
      * @param password the password for the database access 
      */ 
     public InfrastructureDatabaseConfig(String databaseUrl, String username, String password) { 
         this.databaseUrl = databaseUrl; 
         this.username = username; 
         this.password = password; 
         validateConfig(); 
     } 
  
     /** 
      * Validates the configuration settings and throws DatabaseConfigurationException if any are invalid. 
      */ 
     private void validateConfig() { 
         logger.info("Starting configuration validation."); 
         if (databaseUrl == null || databaseUrl.isEmpty() || username == null || username.isEmpty() || password == null || password.isEmpty()) { 
             throw new DatabaseConfigurationException("Invalid database configuration: Ensure all fields are provided."); 
         } 
         logger.info("Configuration validation completed successfully."); 
     } 
  
     // Getters and setters are provided by Lombok through the @Getter and @Setter annotations 
 } 
  
 // This class should be in a separate file named DatabaseConfigurationException.java 
 /** 
  * Custom exception for database configuration errors. 
  */ 
 class DatabaseConfigurationException extends RuntimeException { 
     public DatabaseConfigurationException(String message) { 
         super(message); 
     } 
 }