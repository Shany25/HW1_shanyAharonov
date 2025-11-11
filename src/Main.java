import model.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Message> messages = new ArrayList<>();
        int choice = 0;
        defaultObjects(messages); // Adds default messages
        while (choice != 7) {
            showMenu(); // Shows the menu fot the program
            choice = getUserChoice(scanner); // Gets user choice
            switch (choice) {
                case 1 -> addMessage(scanner, messages); // Add new message
                case 2 -> deleteMessage(scanner, messages); // Delete a specific message
                case 3 -> printAllMessages(messages); // Print all messages
                case 4 -> searchMessagesByWords(scanner, messages); // Search messages by word
                case 5 -> printDigitalMessages(messages); // Print all digital messages
                case 6 -> printPreviews(messages); // Print all message previews
                case 7 -> System.out.println("Exiting program..."); // Exit the program
            }
        }
        scanner.close();
    }

    /**
     * Adds default messages to the list.
     * Includes board messages, email messages, and reaction messages.
     */
    private static void defaultObjects(ArrayList<Message> messages) {
        try {
            /* Boarder Messages: */
            messages.add(new BoardMessage("Alice", "Meeting at 10 AM", Priority.URGENT));
            messages.add(new BoardMessage("Bob", "Weekly report submission deadline", Priority.REGULAR));

            /* Reaction Messages: */
            ArrayList<ReactionMessage> reactionMessages = new ArrayList<>();
            reactionMessages.add(new ReactionMessage("Shay", "Love this idea!", ReactionMessage.ReactionType.LOVE));
            reactionMessages
                    .add(new ReactionMessage("Frank", "Interesting perspective.", ReactionMessage.ReactionType.LAUGH));
            messages.add(new BoardMessage("Lili", "Birthday party on Sunday.", Priority.SPECIAL, reactionMessages));

            /* Email Messages: */
            ArrayList<File> emailAttachments1 = new ArrayList<>();
            emailAttachments1.add(new File("Document1", "pdf"));
            emailAttachments1.add(new File("Presentation1", "ppt"));
            messages.add(new EmailMessage("Shany", "Here are the documents for review.", "Documents Review",
                    emailAttachments1));

            ArrayList<File> emailAttachments2 = new ArrayList<>();
            emailAttachments2.add(new File("Image1", "jpg"));
            messages.add(
                    new EmailMessage("Sam", "Check out this amazing picture!", "Amazing Picture", emailAttachments2));

            /* Reaction Messages: */
            messages.add(new ReactionMessage("Lili", "Love this idea!", ReactionMessage.ReactionType.LOVE));
            messages.add(new ReactionMessage("Yoni", "Interesting perspective.", ReactionMessage.ReactionType.LAUGH));

        } catch (ReactionException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays the main menu of the message system.
     */
    private static void showMenu() {
        String menu = """
                ####  MESSAGES SYSTEM MENU  ####
                (1) Add new message.
                (2) Delete a message.
                (3) Print all messages.
                (4) Search messages by words.
                (5) Print all digital messages.
                (6) Print all message previews.
                (7) Exit.
                """;
        System.out.print(menu);
        System.out.print("Please enter your choice: ");
    }

    /**
     * Gets the user's choice for the menu and validates it.
     * 
     * @param scanner Scanner object for user input.
     * @return Validated user choice.
     */
    private static int getUserChoice(Scanner scanner) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 7) {
                    throw new IllegalArgumentException("Choice must be between 1-7!");
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.print("Choice must be between 1-7!\nTry again: ");
            } catch (IllegalArgumentException e) {
                System.out.print(e.getMessage() + "\nPlease try again: ");
            }
        }
    }

    /**
     * Allows the user to add a new message of a specific type
     * (Board/Email/Reaction).
     * 
     * @param scanner  Scanner object for user input.
     * @param messages List of messages to which the new message will be added.
     */
    private static void addMessage(Scanner scanner, ArrayList<Message> messages) {
        String str = """
                ####  ADD NEW MESSAGE  ####
                (1) Board Message.
                (2) Email Message.
                (3) Reaction.
                Please enter your choice:\s""";
        while (true) {
            try {
                System.out.print(str);
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> messages.add(addBoardMessage(scanner));
                    case 2 -> messages.add(addEmailMessage(scanner));
                    case 3 -> addReactionMessage(scanner, messages);
                    default -> throw new IllegalArgumentException("Choice must be between 1-3!");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1-3.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\nPlease try again.");
            }
        }
    }

    /**
     * Gets the sender and content of a message from the user.
     * 
     * @param scanner Scanner object for user input.
     * @return Array containing sender and content.
     */
    private static String[] getMessageData(Scanner scanner) {
        System.out.println("#### GET MESSAGE DATA ####");
        while (true) {
            try {
                System.out.print("Please enter the sender name: ");
                String sender = scanner.nextLine();
                if (sender == null || sender.isBlank()) {
                    throw new IllegalArgumentException("Sender name cannot be blank!");
                }
                System.out.print("Please enter the message content: ");
                String content = scanner.nextLine();
                if (content == null || content.isBlank()) {
                    throw new IllegalArgumentException("Message content cannot be blank!");
                }
                return new String[] { sender.trim(), content.trim() };
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\nPlease try again!");
            }
        }
    }

    /**
     * Adds a new BoardMessage after getting user input for priority and the message
     * info.
     * 
     * @param scanner Scanner object for user input.
     * @return A new BoardMessage object.
     */
    private static Message addBoardMessage(Scanner scanner) {
        System.out.println("####  ADD BOARD MESSAGE  ####");
        int choice;
        boolean valid = false;
        BoardMessage boardMessage = null;
        String[] data = getMessageData(scanner);
        while (!valid) {
            try {
                System.out.print("Priority:\n(1) Urgent\n(2) Regular\n(3) Special\nPlease enter the priority: ");
                choice = Integer.parseInt(scanner.nextLine());
                boardMessage = switch (choice) {
                    case 1 -> new BoardMessage(data[0], data[1], Priority.URGENT);
                    case 2 -> new BoardMessage(data[0], data[1], Priority.REGULAR);
                    case 3 -> new BoardMessage(data[0], data[1], Priority.SPECIAL);
                    default -> throw new IllegalArgumentException("Choice must be between 1-3!");
                };
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1-3.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\nPlease try again.");
            }
        }
        return boardMessage;
    }

    /**
     * Adds a new EmailMessage after getting user input for subject, attachments and
     * the message info.
     * 
     * @param scanner Scanner object for user input.
     * @return A new EmailMessage object.
     */
    private static Message addEmailMessage(Scanner scanner) {
        System.out.println("####  ADD EMAIL MESSAGE  ####");
        int choice;
        String[] data = getMessageData(scanner);
        while (true) {
            try {
                System.out.print("Please enter a subject: ");
                String subject = scanner.nextLine();
                EmailMessage email = new EmailMessage(data[0], data[1], subject);
                System.out.print("Would you like to add attachments?:\n(1) Yes\n(2) No\nPlease enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> {
                        System.out.print("How many attachments? ");
                        int numAttachments = Integer.parseInt(scanner.nextLine());
                        if (numAttachments < 1) {
                            throw new IllegalArgumentException("Number of attachments must be positive!");
                        }
                        for (int i = 0; i < numAttachments; i++) {
                            File file = createFile(scanner);
                            email.addAttachment(file);
                        }
                        return email;
                    }
                    case 2 -> {
                        return email;
                    }
                    default -> throw new IllegalArgumentException("Choice must be between 1-2!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Try again!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\nPlease try again!");
            }

        }
    }

    /**
     * Creates a new File object for an email attachment.
     * 
     * @param scanner Scanner object for user input.
     * @return A new File object.
     */
    private static File createFile(Scanner scanner) {
        System.out.println("#### ADD FILE TO EMAIL ####");
        while (true) {
            try {
                System.out.print("Enter File name: ");
                String fileName = scanner.nextLine();
                System.out.print("Enter File Type: ");
                String fileType = scanner.nextLine();
                return new File(fileName, fileType);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\nPlease try again!");
            }
        }
    }

    /**
     * Adds a ReactionMessage to an existing BoardMessage.
     * 
     * @param scanner  Scanner object for user input.
     * @param messages List of messages to search for the target BoardMessage.
     */
    private static void addReactionMessage(Scanner scanner, ArrayList<Message> messages) {
        System.out.println("#### ADD REACTION MESSAGE ####");

        // 1) Early-guard: no boards
        boolean anyBoard = false;
        for (Message m : messages) {
            if (m instanceof BoardMessage) {
                anyBoard = true;
                break;
            }
        }
        if (!anyBoard) {
            System.out.println("There are no board messages to react on.");
            return;
        }

        // 2) Show boards once
        printAllBoardMessages(messages);

        // 3) Pick target board by ID
        BoardMessage boardMessage = null;
        while (boardMessage == null) {
            System.out.print("Please enter the message Id you want to react on (or 'L' to list again): ");
            String raw = scanner.nextLine().trim();
            if (raw.equalsIgnoreCase("L")) {
                printAllBoardMessages(messages);
                continue;
            }
            try {
                int messageId = Integer.parseInt(raw);
                for (Message message : messages) {
                    if (message instanceof BoardMessage && message.getId() == messageId) {
                        boardMessage = (BoardMessage) message;
                        break;
                    }
                }
                if (boardMessage == null) {
                    System.out.println("No board message found with Id " + messageId + ". Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric Id.");
            }
        }

        // 4) Sender & content
        String[] data = getMessageData(scanner);

        // 5) Pick reaction type
        ReactionMessage.ReactionType reactionType = null;
        while (reactionType == null) {
            try {
                System.out.print("Enter reaction type (1: LIKE, 2: DISLIKE, 3: LAUGH, 4: LOVE): ");
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> reactionType = ReactionMessage.ReactionType.LIKE;
                    case 2 -> reactionType = ReactionMessage.ReactionType.DISLIKE;
                    case 3 -> reactionType = ReactionMessage.ReactionType.LAUGH;
                    case 4 -> reactionType = ReactionMessage.ReactionType.LOVE;
                    default -> System.out.println("Choice must be between 1-4!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter 1-4.");
            }
        }

        // 6) Create & attach reaction
        try {
            ReactionMessage reaction = new ReactionMessage(data[0], data[1], reactionType);
            boardMessage.addReaction(reaction);
            System.out.println("Reaction added successfully.");
        } catch (IllegalArgumentException | ReactionException e) {
            System.out.println(e.getMessage() + "\nPlease try again!");
        }
    }

    /**
     * Prints all messages in the system.
     * 
     * @param messages List of messages to print.
     */
    private static void printAllMessages(ArrayList<Message> messages) {
        System.out.println("#### PRINT ALL MESSAGES ####");
        if (messages.isEmpty()) {
            System.out.println("No messages to display.");
            return;
        } else {
            for (Message message : messages) {
                System.out.println("Message Type: " + message.getMessageType() + "\n" + message);
            }
        }
    }

    /**
     * Deletes a specific message by its ID.
     * 
     * @param scanner  Scanner object for user input.
     * @param messages List of messages to search and delete from.
     */
    private static void deleteMessage(Scanner scanner, ArrayList<Message> messages) {
        System.out.println("#### DELETE MESSAGE ####");
        if (messages.isEmpty()) {
            System.out.println("No messages to delete.");
            return;
        }
        printAllMessages(messages);
        while (true) {
            try {
                System.out.print("Please enter the message Id you want to delete: ");
                int messageId = Integer.parseInt(scanner.nextLine());
                boolean removed = messages.removeIf(m -> m.getId() == messageId);
                if (removed) {
                    System.out.println("Message deleted successfully.");
                    return;
                } else {
                    System.out.println("Invalid message Id. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid numeric Id.");
            }
        }
    }

    /**
     * Prints all messages that implement the IDigital interface.
     * 
     * @param messages List of messages to print.
     */
    private static void printDigitalMessages(ArrayList<Message> messages) {
        System.out.println("#### PRINT DIGITAL MESSAGES ####");
        if (messages.isEmpty()) {
            System.out.println("No messages to display.");
            return;
        }
        for (Message message : messages) {
            if (message instanceof IDigital) {
                System.out.println("Message Type: " + message.getMessageType() + "\n" + message);
            }
        }
    }

    /**
     * counts and searches for messages containing a specific word.
     * 
     * @param scanner  Scanner object for user input.
     * @param messages List of messages to search from.
     */
    private static void searchMessagesByWords(Scanner scanner, ArrayList<Message> messages) {
        System.out.println("#### SEARCH MESSAGES BY WORDS ####");
        System.out.print("Enter word(s) (comma separated): ");
        String line = scanner.nextLine();

        String[] parts = line.split(",");
        ArrayList<String> words = new ArrayList<>();
        for (String p : parts) {
            String w = (p == null) ? "" : p.trim();
            if (!w.isBlank())
                words.add(w);
        }
        if (words.isEmpty()) {
            System.out.println("No valid words provided.");
            return;
        }

        int count = 0;
        for (Message message : messages) {
            if (message.find(words)) {
                count++;
            }
        }
        System.out.println("Number of messages containing any of " + words + ": " + count);
    }

    /**
     * Prints all messages of type BoardMessage.
     * 
     * @param messages List of messages to filter and print.
     */
    private static void printAllBoardMessages(ArrayList<Message> messages) {
        System.out.println("#### PRINT BOARD MESSAGES ####");
        if (messages.isEmpty()) {
            System.out.println("No messages to display.");
            return;
        }
        for (Message message : messages) {
            if (message instanceof BoardMessage) {
                System.out.println("Message Type: " + message.getMessageType() + "\n" + message);
            }
        }
    }

    /**
     * * Prints previews of all messages.
     * 
     * @param messages
     */
    private static void printPreviews(ArrayList<Message> messages) {
        System.out.println("#### PRINT PREVIEWS ####");
        if (messages.isEmpty()) {
            System.out.println("No messages to display.");
            return;
        }
        for (Message message : messages) {
            System.out.println(message.generatePreview());
        }
    }

}