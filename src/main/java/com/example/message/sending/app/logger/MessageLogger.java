package com.example.message.sending.app.logger;

import com.example.message.sending.app.model.Message;
import com.example.message.sending.app.model.User;
import com.example.message.sending.app.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This class collects and displays messages
 *
 * @author Burak Unlu
 */
public class MessageLogger {

    private static final Logger logger = Logger.getLogger(MessageLogger.class.getName());

    private final List<Message> messages = new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Function that adds a new message to the messages list
     *
     * @param content String message content
     * @param user  User that interacts with the message
     * @throws IllegalArgumentException
     */
    public void logMessage(String content, User user, Boolean sent) {
        if (!StringUtils.isStringValid(content)) {
            throw new IllegalArgumentException("Message is not valid");
        }

        Message message = new Message(content, user, sent);
        messages.add(message);
    }

    /**
     * Function that retrieves sent messages from messages
     */
    public List<Message> getSentMessages() {
        return messages.stream().filter(Message::isSent).collect(Collectors.toList());
    }

    /**
     * Function that retrieves received messages from messages
     */
    public List<Message> getReceivedMessages() {
        return messages.stream().filter(message -> !message.isSent()).collect(Collectors.toList());
    }

    /**
     * Function that displays all messages
     */
    public void logAllMessages() {
        messages.forEach(e -> logger.info(e.toString()));
    }

    /**
     * Function that displays all messages sent
     */
    public void logAllSentMessages() {
        getSentMessages().forEach(e -> logger.info(e.toString()));
    }

    /**
     * Function that displays all messages received
     */
    public void logAllReceivedMessages() {
        getReceivedMessages().forEach(e -> logger.info(e.toString()));
    }

}
