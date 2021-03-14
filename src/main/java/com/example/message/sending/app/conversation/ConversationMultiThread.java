package com.example.message.sending.app.conversation;

import com.example.message.sending.app.observer.Topic;
import com.example.message.sending.app.logger.MessageLogger;
import com.example.message.sending.app.model.User;
import com.example.message.sending.app.observer.SubscriberMultiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * This class starts the conversation between observer through different threads
 * Consists reference of 2 user that is communicating
 * The Observer Pattern used to manage carrying messages between the users
 *
 * @author Burak Unlu
 */
public class ConversationMultiThread implements Conversation {

    private static final Logger logger = Logger.getLogger(ConversationMultiThread.class.getName());

    private User initiator;
    private User user;

	/**
	 * Function that adds initiator user to the conversation
     * @param user User start the conversation
	 */
    @Override
    public void addInitiator(User user) {
        this.initiator = user;
    }

	/**
	 * Function that adds other user to the conversation
     * @param user User represents other user in the conversation
	 */
    @Override
    public void addUser(User user) {
        this.user = user;
    }

    /**
     * Function that starts the conversation between the users
     * Observers are stored in different threads
     * @param message String first message that starts the conversation
     *
     * @return MessageLogger to display messages
     */
    public MessageLogger start(String message) {

        logger.info("(Multi) Starting conversation...");

        Topic topic = new Topic();
        SubscriberMultiThread initiatorSubscriberMultiThread = new SubscriberMultiThread(topic, initiator);
        SubscriberMultiThread subscriberMultiThread = new SubscriberMultiThread(topic, user);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(new ConversationThread(initiatorSubscriberMultiThread, message));
        executor.submit(new ConversationThread(subscriberMultiThread));

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warning("Thread is interrupted");
        }

        topic.getMessageLogger().logAllSentMessages();

        logger.info("Conversation ended...");

        return topic.getMessageLogger();
    }
}
