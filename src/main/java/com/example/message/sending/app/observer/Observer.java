package com.example.message.sending.app.observer;

import com.example.message.sending.app.model.User;

/**
 * This interface is the Interface that allows user to start conversation and receive message from the subscribed Observer.
 *
 * @author Burak Unlu
 */
public interface Observer {

    User getUser();

    /**
     * Function that initiates the chat and send the first message to all the other observers
     *
     * @param message String the first message be sent in the chat
     */
    void conversationStart(String message);

    /**
     * Function that receives a message over the topic
     * Logs the notification of the receiving the message
     *
     * @param sender User the sender of the message
     * @param message    String the message content
     */
    void receive(User sender, String message);

    /**
     * Function that sends a message over the topic
     * Triggered after a message received
     * Logs the notification of the sending the message
     */
    void send();

}
