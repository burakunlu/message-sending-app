package com.example.message.sending.app.conversation;

import com.example.message.sending.app.observer.SubscriberMultiThread;

/**
 * This class creates observer thread and allows start conversation
 * Used to carry message through different threads from observers
 *
 * @author Burak Unlu
 */
public class ConversationThread implements Runnable {

    private final SubscriberMultiThread observer;

    /**
     * Constructor that initializes the thread
     *
     * @param observer Subscriber reference of the observer object
     */
    public ConversationThread(SubscriberMultiThread observer) {
        this.observer = observer;
    }

    /**
     * Constructor that initializes the thread and start the conversation between the observers
     *
     * @param observer Subscriber reference of the observer object
     * @param message  String the first message to be sent
     */
    public ConversationThread(SubscriberMultiThread observer, String message) {
        this.observer = observer;
        this.observer.conversationStart(message);
    }

    /**
     * Function that runs the thread
     * While complete condition is not complete, receives a new message and sends it back to other observers
     */
    @Override
    public void run() {
        while (this.observer.isSendingCompleted()) {
            this.observer.send();
        }
    }

}
