package com.example.message.sending.app.conversation.exception;

/**
 * This class is used to throw error in case something goes wrong during conversation
 *
 * @author Burak Unlu
 */
public class ConversationException extends RuntimeException {

    public ConversationException(String message) {
        super(message);
    }
}