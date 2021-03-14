package com.example.message.sending.app.conversation;

import com.example.message.sending.app.conversation.exception.ConversationException;

/**
 * This class is used to create conversation through one of the conversation types using Factory Pattern
 * @author Burak Unlu
 */
public class ConversationFactory {

    /**
     * Function that returns single or multi thread conversation instances that allows to start conversation
     * @param type String conversation type
     */
    public Conversation getConversation(String type) {

        if (type.equalsIgnoreCase("multi")) {
            return new ConversationMultiThread();
        } else if (type.equalsIgnoreCase("single")) {
            return new ConversationSingleThread();
        }

        throw new ConversationException("Conversation type must be defined as 'multi' or 'single'");
    }
}
