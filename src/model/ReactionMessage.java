package model;

/**
 * Represents a reaction message extending the Message class. indicating a
 * reaction (like, dislike, laugh, or love).
 * Includes a specific reaction type.
 */
public class ReactionMessage extends Message {
    /** Enum representing the reaction types. */
    public enum ReactionType {
        LIKE, DISLIKE, LAUGH, LOVE
    }

    /** The type of reaction. */
    private ReactionType reactionType;

    // ======= Getter and Setter ======= //
    /**
     * Gets the reaction type of the message.
     * 
     * @return The reaction type.
     */
    public ReactionType getReactionType() {
        return reactionType;
    }

    /**
     * Sets the reaction type for the message.
     * Validates that the reaction type is not null.
     * 
     * @param reactionType The reaction type to set.
     * @throws ReactionException If the reaction type is null.
     */
    public void setReactionType(ReactionType reactionType) throws ReactionException {
        if (reactionType == null) {
            throw new ReactionException("Reaction type cannot be null.");
        }
        this.reactionType = reactionType;
    }

    // ======= Constructors ======= //
    /**
     * Default constructor.
     * Initializes the reaction message with no sender, content.
     * Default reaction type of LIKE.
     */
    public ReactionMessage() {
        super();
        reactionType = ReactionType.LIKE;
    }

    /**
     * Constructs a ReactionMessage with sender, content and reaction type.
     * 
     * @param sender       The name of the sender.
     * @param content      The content of the reaction message.
     * @param reactionType The type of the reaction.
     * @throws IllegalArgumentException If the sender or content is invalid.
     * @throws ReactionException        If the reaction type is invalid.
     */
    public ReactionMessage(String sender, String content, ReactionType reactionType)
            throws ReactionException {
        super(sender, content);
        setReactionType(reactionType);
    }

    /**
     * Returns the type of the message as "Reaction".
     * 
     * @return A string representing the message type.
     */
    @Override
    public String getMessageType() {
        return "Reaction";
    }

    /**
     * Returns a shortened preview in the format:
     * [Reaction] {sender}: {REACTION} - {first 15 chars of content}...
     */
    @Override
    public String generatePreview() {
        String who = (sender == null) ? "" : sender;
        String reaction = (reactionType == null) ? "" : reactionType.name();
        String text = (content == null) ? "" : content;
        String preview = text.length() > 15 ? text.substring(0, 15).trim() + "..." : text;
        return "[Reaction] " + who + ": " + reaction + " - " + preview;
    }

    /**
     * Returns a string representation of the reaction message.
     * Includes the sender's name, reaction type and message content.
     * 
     * @return The string representation of the reaction message.
     */
    @Override
    public String toString() {
        return "Reaction Message\n" +
                "Sender: " + getSender() + "\n" +
                "Reaction: " + getReactionType() + "\n" +
                "Content: " + getContent() + "\n";
    }
}
