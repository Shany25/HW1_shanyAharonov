package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents an email message extending the Message class.
 * Implements the IDigital interface to indicate it's a digital communication
 * method.
 * Includes a subject and a list of file attachments.
 * Implements IDigital per assignment and returns the required string in
 * printCommunicationMethod().
 */
public class EmailMessage extends Message implements IDigital {
    /** The subject of the email. */
    private String subject;
    /** A list of files attached to the email. */
    private ArrayList<File> attachments;

    // ======= Getters and Setters ======= //
    /**
     * Gets the subject of the email.
     * 
     * @return The subject as a string.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the email.
     *
     * @param subject The subject of the email.
     * @throws IllegalArgumentException If the subject is null or blank.
     */
    public void setSubject(String subject) {
        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Subject cannot be null or blank");
        }
        this.subject = subject.trim();
    }

    /**
     * Gets a copy of the attachments list (defensive copy to avoid external
     * mutation).
     *
     * @return A new ArrayList containing the attachments.
     */
    public ArrayList<File> getAttachments() {
        return new ArrayList<>(attachments);
    }

    /**
     * Sets the list of attachments. If null is provided, initializes an empty list.
     *
     * @param attachments The list of attachments to set.
     */
    public void setAttachments(ArrayList<File> attachments) {
        if (attachments == null) {
            this.attachments = new ArrayList<>();
        } else {
            this.attachments = new ArrayList<>(attachments);
        }
    }

    // ======= Methods & Constructors ======= //
    /**
     * Default constructor.
     * Initializes an email message with no sender, content or subject.
     * Attachments are initialized to empty list.
     */
    public EmailMessage() {
        super();
        this.attachments = new ArrayList<>();
    }

    /**
     * Constructs an EmailMessage with sender, content, subject and attachments (no
     * date, time provided).
     * Delegates to the full constructor with current system date, time.
     *
     * @param sender      The name of the sender.
     * @param content     The content of the email.
     * @param subject     The subject of the email.
     * @param attachments The list of file attachments.
     * @throws IllegalArgumentException If sender, content or subject is invalid.
     */
    public EmailMessage(String sender, String content, String subject, ArrayList<File> attachments) {
        this(sender, content, subject, new Date(), new Date(), attachments);
    }

    /**
     * Constructs an EmailMessage with sender, content and subject.
     * Initializes an empty list of file attachments.
     *
     * @param sender  The name of the sender.
     * @param content The content of the email.
     * @param subject The subject of the email.
     * @throws IllegalArgumentException If sender, content or subject is invalid.
     */
    public EmailMessage(String sender, String content, String subject) {
        this(sender, content, subject, new Date(), new Date(), new ArrayList<>());
    }

    /**
     * Full constructor with all fields (including inherited date/time).
     * Initializes fields only if valid via setters to avoid code duplication.
     *
     * @param sender      The name of the sender.
     * @param content     The content of the email.
     * @param subject     The subject of the email.
     * @param sendDate    The sending date.
     * @param sendTime    The sending time.
     * @param attachments The list of file attachments.
     * @throws IllegalArgumentException If any validated field is invalid.
     */
    public EmailMessage(String sender, String content, String subject, Date sendDate, Date sendTime,
            ArrayList<File> attachments) {
        super(sender, content, sendDate, sendTime);
        setSubject(subject);
        setAttachments(attachments);
    }

    // ======= Attachments Management ======= //
    /**
     * Adds a file to the list of attachments.
     *
     * @param file The file to add.
     * @throws IllegalArgumentException if file is null.
     */
    public void addAttachment(File file) {
        if (file == null) {
            throw new IllegalArgumentException("Attachment cannot be null");
        }
        this.attachments.add(file);
    }

    /**
     * Removes all occurrences of a given file from the attachments list.
     *
     * @param file The file to remove.
     * @throws AttachmentException If the file is not present in the attachments
     *                             list.
     */
    public void removeAttachment(File file) throws AttachmentException {
        if (file == null) {
            throw new AttachmentException("Attachment cannot be null");
        }
        int before = this.attachments.size();
        this.attachments.removeIf(att -> att != null && att.equals(file));
        int removed = before - this.attachments.size();
        if (removed == 0) {
            throw new AttachmentException("Attachment does not exist!");
        }
    }

    /**
     * Returns the type of the message as "Email".
     * 
     * @return The type of the message.
     */
    @Override
    public String getMessageType() {
        return "Email";
    }

    /**
     * Implementation of IDigital: returns the exact required string.
     *
     * @return " Sent via Email Server"
     */
    @Override
    public String printCommunicationMethod() {
        return "Sent via Email Server";
    }

    /**
     * Returns a concise preview in the format:
     * [Email] Subject: {subject} | From: {sender}
     */
    @Override
    public String generatePreview() {
        String subj = (subject == null || subject.isBlank()) ? "" : subject.trim();
        String who = (sender == null) ? "" : sender;
        return "[Email] Subject: " + subj + " | From: " + who;
    }

    /**
     * Returns a string representation of the email including its subject, sender,
     * content and list of file attachments.
     * 
     * @return The string representation of the email.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("subject:").append(getSubject()).append("\n");
        sb.append(super.toString()).append("\n");
        if (attachments.isEmpty()) {
            sb.append("Attachment List: None.\n");
        } else {
            sb.append("Attachment List:\n");
            for (File file : attachments) {
                sb.append("- ").append(file).append("\n");
            }
        }
        return sb.toString();
    }
}
