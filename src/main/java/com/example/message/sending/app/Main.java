package com.example.message.sending.app;

import com.example.message.sending.app.conversation.Conversation;
import com.example.message.sending.app.conversation.ConversationFactory;
import com.example.message.sending.app.model.User;
import com.example.message.sending.app.util.StringUtils;

/**
 * This application is designed to carry messages between users using Observer Pattern
 * Receives conversation type(single, multi), initial message and user name as command line argument to start the conversation
 *
 * @author Burak Unlu
 */
public class Main {

    public static void main(String[] args) {
        String firstMessage = StringUtils.getFirstMessage(args);
        String userName = StringUtils.getUserName(args);
        String type = StringUtils.getConversationType(args);

        User initiator = new User("initiator");
        User user = new User(userName);

        ConversationFactory conversationFactory = new ConversationFactory();

        Conversation conversation = conversationFactory.getConversation(type);
        conversation.addInitiator(initiator);
        conversation.addUser(user);

        conversation.start(firstMessage);

        System.exit(0);
    }

}
