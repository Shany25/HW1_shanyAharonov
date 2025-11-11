package model;

/**
 * Custom exception class for handling errors related to reactions.
 * Extends the base Exception class.
 */
public class ReactionException extends Exception {
    /**
     * Constructs a new ReactionException with the specified detail message.
     * @param message The detail message explaining the cause of the exception.
     */
    public ReactionException(String message) {
        super(message);
    }
}

