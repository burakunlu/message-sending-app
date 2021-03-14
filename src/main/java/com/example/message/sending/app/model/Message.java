package com.example.message.sending.app.model;

/**
 * This class is the representation of message
 *
 * @author Burak Unlu
 */
public class Message {

    private final String content;

    private final User user;

    private final Boolean sent;

    /**
     * Constructor that initializes the Message class
     *
     * @param content String message content
     * @param user  User interacts with the message
     * @param sent    Boolean that sets the flag if message sent or received
     */
    public Message(String content, User user, Boolean sent) {
        this.content = content;
        this.user = user;
        this.sent = sent;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public Boolean isSent() {
        return sent;
    }

    @Override
    public String toString() {
        if (isSent()) {
            return user.getName() + " sending: '" + content + "'";
        }
        return user.getName() + " receiving: '" + content + "'";
    }
}