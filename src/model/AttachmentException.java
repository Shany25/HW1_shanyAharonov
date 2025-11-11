package model;

/**
 * Custom exception class for handling errors related to file attachments.
 * Extends the base Exception class.
 */
public class AttachmentException extends Exception {
    /**
     * Constructs a new AttachmentException with the specified detail message.
     * @param message The detail message explaining the cause of the exception.
     */
    public AttachmentException(String message) {
        super(message);
    }
}

