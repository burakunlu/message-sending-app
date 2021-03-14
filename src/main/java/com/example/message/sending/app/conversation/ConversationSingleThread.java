package com.example.message.sending.app.conversation;

import com.example.message.sending.app.logger.MessageLogger;
import com.example.message.sending.app.model.User;
import com.example.message.sending.app.observer.Topic;
import com.example.message.sending.app.observer.SubscriberSingleThread;

import java.util.logging.Logger;

/**
 * This class starts the conversation between observer through a single process
 * Consists reference of 2 user that is communicating
 * The Observer Pattern used to manage carrying messages between the users
 *
 * @author Burak Unlu
 */
public class ConversationSingleThread implements Conversation {

    private static final Logger logger = Logger.getLogger(ConversationSingleThread.class.getName());

    private User initiator;
    private User user;

    @Override
    public void addInitiator(User user) {
        this.initiator = user;
    }

    @Override
    public void addUser(User user) {
        this.user = user;
    }

    /**
     * Function that starts the conversation between the users
     * Observers are hold in different threads
     *
     * @return
     */
    @Override
    public MessageLogger start(String message) {

        logger.info("(Single) Starting conversation...");

        Topic topic = new Topic();
        SubscriberSingleThread initiatorSubscriber = new SubscriberSingleThread(topic, initiator);
        SubscriberSingleThread subscriber = new SubscriberSingleThread(topic, user);

        initiatorSubscriber.conversationStart(message);

        topic.getMessageLogger().logAllSentMessages();

        logger.info("Conversation ended...");

        return topic.getMessageLogger();
    }

}
