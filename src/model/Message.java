package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents an abstract base class for messages in the system.
 * Each message has a sender, content, date ,time, and a unique ID.
 * Subclasses must implement the abstract getMessageType method.
 */
public abstract class Message {
    /** The sender of the message. */
    protected String sender;
    /** The content of the message. */
    protected String content;
    /** The date the message was sent. */
    protected Date sendDate;
    /** The time the message was sent. */
    protected Date sendTime;
    /** Unique id for the message. */
    private int id;
    /** Static counter for generating unique IDs for each message. */
    private static int nextId = 1;

    // ======= Getters and Setters ======= //
    /**
     * Sets the sender of the message.
     * 
     * @param sender The name of the sender.
     * @throws IllegalArgumentException If the sender is null or empty.
     */
    protected void setSender(String sender) {
        if (sender == null || sender.isBlank()) {
            throw new IllegalArgumentException("Senders name cannot be empty");
        }
        this.sender = sender.trim();
    }

    /**
     * Gets the sender of the message.
     * 
     * @return The sender's name.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the content of the message.
     * 
     * @param content The content of the message.
     * @throws IllegalArgumentException If the content is null or empty.
     */
    protected void setContent(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Message content cannot be empty");
        }
        this.content = content.trim();
    }

    /**
     * Gets the content of the message.
     * 
     * @return The content of the message.
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the date the message was sent.
     * 
     * @return The date the message was sent.
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * Sets the date the message was sent.
     * 
     * @param sendDate date to set
     * @throws IllegalArgumentException if sendDate is null
     */
    protected void setSendDate(Date sendDate) {
        if (sendDate == null) {
            throw new IllegalArgumentException("sendDate cannot be null");
        }
        this.sendDate = sendDate;
    }

    /**
     * Gets the time the message was sent.
     * 
     * @return The time the message was sent.
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * Sets the time the message was sent.
     * 
     * @param sendTime time to set
     * @throws IllegalArgumentException if sendTime is null
     */
    protected void setSendTime(Date sendTime) {
        if (sendTime == null) {
            throw new IllegalArgumentException("sendTime cannot be null");
        }
        this.sendTime = sendTime;
    }

    /** @return unique message ID. */
    public int getId() {
        return id;
    }

    // ======= Constructors ======= //

    /** Default constructor: initializes id and current date, time. */
    public Message() {
        id = nextId++;
        sendDate = new Date();
        sendTime = new Date();
    }

    /**
     * Constructs a new Message obj with a sender, content, date and time.
     * The message is automatically assigned to a unique ID.
     * 
     * @param sender   The name of the sender.
     * @param content  The content of the message.
     * @param sendDate The date the message was sent.
     * @param sendTime The time the message was sent.
     * @throws IllegalAccessException If the sender or content is invalid.
     */
    public Message(String sender, String content, Date sendDate, Date sendTime) {
        this.id = nextId++;
        setSender(sender);
        setContent(content);
        setSendDate(sendDate);
        setSendTime(sendTime);
    }

    /**
     * Constructs a new Message obj with a sender and content.
     * The message is automatically assigned to a unique ID and current date/time.
     * 
     * @param sender  The name of the sender.
     * @param content The content of the message.
     * @throws IllegalAccessException If the sender or content is invalid.
     */
    public Message(String sender, String content) {
        this(sender, content, new Date(), new Date());
    }

    // ======= Methods ======= //

    /**
     * Searches the content of the message for any of the provided words.
     * 
     * @param words A list of words to search for.
     * @return True if any word is found in the content otherwise false.
     */
    public boolean find(ArrayList<String> words) {
        if (words == null || words.isEmpty() || content == null)
            return false;
        String text = content.toLowerCase();
        for (String w : words) {
            if (w != null && !w.isBlank() && text.contains(w.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a short preview string of this message according to its concrete
     * type.
     * Subclasses must implement this method.
     */
    public abstract String generatePreview();

    /**
     * Gets the type of the message.
     * Subclasses must implement this method.
     * 
     * @return A string representing the type of the message.
     */
    public abstract String getMessageType();

    /**
     * Returns a string representation of the message including its ID, sender,
     * content, date and time.
     * 
     * @return The string representation of the message.
     */
    @Override
    public String toString() {
        return "Message ID: " + id
                + "\nSender: " + sender
                + "\nContent: " + content
                + "\nDate: " + sendDate
                + "\nTime: " + sendTime;
    }
}
