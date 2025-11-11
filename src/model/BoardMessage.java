package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a board message extending the Message class.
 * Includes a priority and reactions list.
 */
public class BoardMessage extends Message {
    /** The priority of the board message. */
    private Priority priority;
    /** A list of reactions to the board message. */
    private ArrayList<ReactionMessage> reactions;

    // ======= Getters and Setters ======= //

    /**
     * Gets the priority of the board message.
     * 
     * @return The priority as an enum value.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the board message.
     *
     * @param priority The priority to set (URGENT/REGULAR/SPECIAL).
     * @throws IllegalArgumentException if priority is null.
     */
    public void setPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("priority cannot be null");
        }
        this.priority = priority;
    }

    /**
     * Sets the reactions to the board message.
     * Initializes an empty list if the provided list is null.
     * 
     * @param reactions A list of reactions to set.
     */
    public void setReactions(ArrayList<ReactionMessage> reactions) {
        if (reactions == null) {
            this.reactions = new ArrayList<>();
            return;
        }
        this.reactions = reactions;
    }

    /**
     * Gets a copy of the reactions list to avoid external mutations.
     * 
     * @return a copy of current reactions list.
     */
    public ArrayList<ReactionMessage> getReactions() {
        return new ArrayList<>(reactions);
    }

    // ======= Methods & Constructors ======= //

    /**
     * Default constructor.
     * Initializes the board message with no sender, content.
     * Default priority of REGULAR and an empty list of reactions.
     */
    public BoardMessage() {
        super();
        priority = Priority.REGULAR;
        reactions = new ArrayList<>();
    }

    /**
     * Constructs a BoardMessage with sender, content and priority.
     * Initializes an empty list of reactions.
     *
     * @param sender   The sender of the message.
     * @param content  The content of the message.
     * @param priority The priority of the message.
     */
    public BoardMessage(String sender, String content, Priority priority) {
        this(sender, content, new Date(), new Date(), priority, new ArrayList<>());
    }

    /**
     * Constructs a BoardMessage with sender, content, priority and an initial list
     * of reactions.
     *
     * @param sender    The sender of the message.
     * @param content   The content of the message.
     * @param priority  The priority of the message.
     * @param reactions The list of reactions.
     */
    public BoardMessage(String sender, String content, Priority priority, ArrayList<ReactionMessage> reactions) {
        this(sender, content, new Date(), new Date(), priority, reactions);
    }

    /**
     * Full constructor with all fields (including inherited date and time).
     * Initializes fields only if valid via setters to avoid code duplication.
     *
     * @param sender    The sender of the message.
     * @param content   The content of the message.
     * @param sendDate  The sending date.
     * @param sendTime  The sending time.
     * @param priority  The priority (URGENT/REGULAR/SPECIAL).
     * @param reactions The list of reactions.
     * @throws IllegalArgumentException if any validated field is invalid.
     */
    public BoardMessage(String sender, String content, Date sendDate, Date sendTime,
            Priority priority, ArrayList<ReactionMessage> reactions) {
        super(sender, content, sendDate, sendTime);
        setPriority(priority);
        setReactions(reactions);
    }

    /**
     * Constructs a BoardMessage with all fields except date, time and priority.
     * Priority defaults to REGULAR.
     *
     * @param sender    The sender of the message.
     * @param content   The content of the message.
     * @param reactions The list of reactions (may be null for empty).
     */
    public BoardMessage(String sender, String content, ArrayList<ReactionMessage> reactions) {
        super(sender, content); // sets current date/time
        setPriority(Priority.REGULAR);
        setReactions(reactions);
    }

    /**
     * Adds a new reaction to the board message.
     * 
     * @param reaction The reaction to add.
     */
    public void addReaction(ReactionMessage reaction) {
        reactions.add(reaction);
    }

    /**
     * Returns the type of the message as "Board".
     * 
     * @return A string representing the type of the message.
     */
    @Override
    public String getMessageType() {
        return "Board";
    }

    /**
     * Returns a shortened preview: "[Board] {sender}: {first 15 chars of
     * content}..."
     */
    @Override
    public String generatePreview() {
        String text = (content == null) ? "" : content;
        String preview = text.length() > 15 ? text.substring(0, 15).trim() + "..." : text;
        String who = (sender == null) ? "" : sender;
        return "[Board] " + who + ": " + preview;
    }

    /**
     * Returns a string representation of the board message. Includes priority,
     * sender, content and reactions.
     * If there are no reactions adds the string: "No Reactions Found."
     * 
     * @return The string representation of the board message.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Priority:").append(getPriority()).append("\n");
        sb.append(super.toString()).append("\n");
        if (reactions.isEmpty()) {
            sb.append("Reactions: No Reactions Found\n");
        } else {
            sb.append("Reactions:\n");
            for (ReactionMessage reaction : reactions) {
                sb.append("- ").append(reaction.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
