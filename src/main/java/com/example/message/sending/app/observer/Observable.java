package com.example.message.sending.app.observer;

/**
 * This class is the interface that allows user to declare Observable for observers to subscribe
 *
 * @author Burak Unlu
 */
public interface Observable {

    /**
     * Function that adds a new observer to the observer list.
     *
     * @param observer Observer to be added
     */
    void register(Observer observer);

    /**
     * Function that deletes an observer to the observer list.
     *
     * @param observer Observer to be deleted
     */
    void unregister(Observer observer);

    /**
     * Function that sends message to other observer
     *
     * @param current Observer the sender of the message
     * @param message String the message content
     */
    void update(Observer current, String message);
}
