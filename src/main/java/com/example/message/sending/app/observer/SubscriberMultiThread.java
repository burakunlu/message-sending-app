package com.example.message.sending.app.observer;

import com.example.message.sending.app.model.User;
import com.example.message.sending.app.util.StringUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class implements Observer interface from the Observer design pattern.
 * Represents each Observer that is receiving the updates from subscribed Observable.
 * Runs through multi thread
 *
 * @author Burak Unlu
 */
public class SubscriberMultiThread implements Observer {

    private static final int MAX_MESSAGE = 10;

    private Observable topic;

    private User user;

    private Queue<String> receivedMessages = new LinkedList<>();

    private AtomicInteger counterReceived = new AtomicInteger(0);

    private AtomicInteger counterSent = new AtomicInteger(0);

    /**
     * Constructor that initializes the Subscriber class
     *
     * @param topic  Observable reference that observers subscribes
     * @param user User observer reference that sends and receives messages
     */
    public SubscriberMultiThread(Observable topic, User user) {
        this.user = user;
        this.topic = topic;
        topic.register(this);
    }

    @Override
    public User getUser() {
        return this.user;
    }

    /**
     * Function that starts the conversation and send the first message to all the other observers
     *
     * @param message String the first message be sent in the chat
     * @throws IllegalArgumentException
     */
    @Override
    public void conversationStart(String message) {
        if (!StringUtils.isStringValid(message)) {
            throw new IllegalArgumentException("Message is not valid");
        }

        counterSent.incrementAndGet();
        topic.update(this, message);
    }

    /**
     * Function that receives a message and sends back a new message to all the observers through subscribed observable
     * display messages through MessageLogger
     *
     * @param sender  User the sender of the message
     * @param message String the message content
     * @throws IllegalArgumentException
     */
    @Override
    public void receive(User sender, String message) {
        if (!StringUtils.isStringValid(message)) {
            throw new IllegalArgumentException("Message is not valid");
        }

        if (isReceivingCompleted()) {
            receivedMessages.add(message);
            counterReceived.incrementAndGet();

            synchronized (this) {
                ((Topic) this.topic).onMessageReceived(message, sender);
            }
        }
    }

    /**
     * Function that sends message by concatenating number of received messages
     */
    @Override
    public void send() {
        if (isReceivingCompleted() && !receivedMessages.isEmpty()) {
            String messageToSend = receivedMessages.peek() + " " + (counterSent.incrementAndGet());
            receivedMessages.remove();
            topic.update(this, messageToSend);
        }
    }

    public boolean isReceivingCompleted() {
        return counterReceived.get() < MAX_MESSAGE;
    }

    public boolean isSendingCompleted() {
        return counterSent.get() < MAX_MESSAGE;
    }
}
