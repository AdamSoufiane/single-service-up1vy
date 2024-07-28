package application;

/**
 * Exception thrown when a requested post is not found in the application.
 */
public class ApplicationPostNotFoundException extends RuntimeException {
    private String message;
    private String details;

    /**
     * Constructs a new exception with the specified detail message and details.
     * @param message the detail message.
     * @param details the detailed information about the exception.
     */
    public ApplicationPostNotFoundException(String message, String details) {
        super(message);
        this.message = message;
        this.details = details;
    }

    /**
     * Constructs a new exception with a default message.
     */
    public ApplicationPostNotFoundException() {
        super("The requested post was not found.");
        this.message = "The requested post was not found.";
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public String getDetails() {
        return details;
    }
}