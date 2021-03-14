package com.example.message.sending.app.observer;

import com.example.message.sending.app.logger.MessageLogger;
import com.example.message.sending.app.model.User;
import com.example.message.sending.app.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements Observable interface from the Observer design pattern.
 * Allows observers to subscribe and communicates through
 *
 * @author Burak Unlu
 */
public class Topic implements Observable {

    private final MessageLogger messageLogger = new MessageLogger();
    private final List<Observer> observers = new ArrayList<>();

    private final Object SYNC = new Object();

    /**
     * Function that adds a new observer to observer list
     *
     * @param observer Observer to be added
     * @throws IllegalArgumentException
     */
    @Override
    public void register(Observer observer) {
        if (observer == null) {
            throw new NullPointerException("Couldn't register the observer");
        }

        synchronized (this) {
            observers.add(observer);
        }
    }

    /**
     * Function that deletes an observer from observer list.
     *
     * @param observer Observer to be deleted
     * @throws IllegalArgumentException
     */
    @Override
    public void unregister(Observer observer) {
        if (observer == null) {
            throw new NullPointerException("Couldn't unregister the observer");
        }

        int observerIndex = observers.indexOf(observer);

        synchronized (this) {
            observers.remove(observerIndex);
        }
    }

    /**
     * Function that sends message to other observer
     *
     * @param currentObserver Observer sender of the message
     * @param message         Message the message content
     * @throws IllegalArgumentException
     */
    @Override
    public void update(Observer currentObserver, String message) {

        if (!StringUtils.isStringValid(message)) {
            throw new IllegalArgumentException("Message is not valid");
        }

        synchronized (this) {
            notifyOtherObservers(currentObserver, message);
        }
    }

    /**
     * Function that carries message through observers
     *
     * @param currentUser Observer the sender of the message
     * @param message       String the message content
     * @throws IllegalArgumentException
     */
    public void notifyOtherObservers(Observer currentUser, String message) {
        if (!StringUtils.isStringValid(message)) {
            throw new IllegalArgumentException("Message is not valid");
        }

        for (Observer observer : observers) {
            // prevent current user sending message to himself
            if (!currentUser.getUser().equals(observer.getUser())) {
                onMessageSent(message, currentUser.getUser());
                observer.receive(currentUser.getUser(), message);
            }
        }
    }

    /**
     * Function that logs the sent message
     *
     * @param content String the message content
     * @param user  Player the message sender
     */
    public void onMessageSent(String content, User user) {
        messageLogger.logMessage(content, user, true);
    }

    /**
     * Function that logs the received message
     *
     * @param content String the message content
     * @param user  Player the message receiver
     */
    public void onMessageReceived(String content, User user) {
        messageLogger.logMessage(content, user, false);
    }

    public MessageLogger getMessageLogger() {
        return messageLogger;
    }
}
