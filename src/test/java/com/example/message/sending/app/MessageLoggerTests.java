package com.example.message.sending.app;

import com.example.message.sending.app.logger.MessageLogger;
import com.example.message.sending.app.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * This class consists tests cases of MessageLogger class
 *
 * @author Burak Unlu
 */
public class MessageLoggerTests {
    MessageLogger logger;
    User sender = new User("sender");
    User receiver = new User("receiver");

    @Before
    public void initObjects() {
        logger = new MessageLogger();
    }

    /**
     * Store sent messages
     *
     * @result 2 sent messages should be stored by Message logger
     */
    @Test
    public void shouldGetNumberOfSentMessages() {
        logger.logMessage("hello", sender, true);
        logger.logMessage("hello again", sender, true);

        assertEquals(2, logger.getSentMessages().size());
    }

    /**
     * Store received messages
     *
     * @result 2 received messages should be stored by Message logger
     */
    @Test
    public void shouldGetNumberOfReceivedMessages() {
        logger.logMessage("hi", receiver, false);
        logger.logMessage("hi again", receiver, false);

        assertEquals(2, logger.getReceivedMessages().size());
    }

    /**
     * Check behaviour when invalid message is passed
     *
     * @result throw IllegalArgument error
     */
    @Test
    public void logMessage_ShouldThrowError_WhenInvalidArgumentPassed() {
        String content = "";

        assertThrows(
                IllegalArgumentException.class, () -> {
                    logger.logMessage(content, receiver, false);
                }
        );
    }
}
