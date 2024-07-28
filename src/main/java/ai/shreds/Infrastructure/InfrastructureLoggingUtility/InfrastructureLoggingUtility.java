package ai.shreds.infrastructure; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.beans.factory.annotation.Value; 
  
 /** 
  * Utility class for logging across the application. 
  * Provides methods to log error and info level messages. 
  */ 
 public class InfrastructureLoggingUtility { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureLoggingUtility.class); 
  
     @Value("${app.logging.level}") 
     private static String loggingLevel; 
  
     /** 
      * Logs error messages. 
      * @param message The error message to log. 
      */ 
     public static void logError(String message) { 
         try { 
             logger.error("Error in " + Thread.currentThread().getStackTrace()[2].getClassName() + ": " + message); 
         } catch (Exception e) { 
             System.err.println("Failed to log error due to: " + e.getMessage()); 
         } 
     } 
  
     /** 
      * Logs informational messages. 
      * @param message The info message to log. 
      */ 
     public static void logInfo(String message) { 
         try { 
             logger.info("Info from " + Thread.currentThread().getStackTrace()[2].getClassName() + ": " + message); 
         } catch (Exception e) { 
             System.err.println("Failed to log info due to: " + e.getMessage()); 
         } 
     } 
 }