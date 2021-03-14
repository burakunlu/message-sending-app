package com.example.message.sending.app.conversation;

import com.example.message.sending.app.logger.MessageLogger;
import com.example.message.sending.app.model.User;

/**
 * This class is the interface that allows start to conversation
 *
 * @author Burak Unlu
 */
public interface Conversation {

    /**
     * Function that starts conversation between subscribed observers
     * @param user User start the conversation
     */
    void addInitiator(User user);

    /**
     * Function that starts conversation between subscribed observers
     * @param user User represents other user in the conversation
     */
    void addUser(User user);

    /**
     * Function that starts conversation between subscribed observers
     * @param message String first message that starts the conversation
     */
    MessageLogger start(String message);

}
