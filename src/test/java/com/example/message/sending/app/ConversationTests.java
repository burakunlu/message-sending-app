package com.example.message.sending.app;

import com.example.message.sending.app.conversation.ConversationMultiThread;
import com.example.message.sending.app.conversation.ConversationSingleThread;
import com.example.message.sending.app.logger.MessageLogger;
import com.example.message.sending.app.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class consists tests cases of conversation through single and multi thread
 *
 * @author Burak Unlu
 */
public class ConversationTests {

    String firstMessage;
    User initiator;
    User user;

    @Before
    public void initObjects() {
        firstMessage = "first";

        initiator = new User("initiator");
        user = new User("otheruser");
    }

    /**
     * Start conversation through multi thread
     *
     * @result Users send messages until initiator sends 10 messages
     * Once 10 message is achieved by initiator, other user should send 10 messages as well
     * In total 20 messages should be delivered
     */
    @Test
    public void startMultiThreadConversation_ShouldStop_WhenMaxMessageNumberAchieved() {
        ConversationMultiThread conversation = new ConversationMultiThread();
        conversation.addInitiator(initiator);
        conversation.addUser(user);
        MessageLogger logger = (conversation.start(firstMessage));

        assertEquals(20, logger.getSentMessages().size());
        assertEquals(20, logger.getReceivedMessages().size());
    }

    /**
     * Start conversation through single thread
     *
     * @result Users send messages until initiator sends 10 messages
     * Once 10 message is achieved by initiator, other user should send 10 messages as well
     * In total 20 messages should be delivered
     */
    @Test
    public void startSingleconversation_ShouldStop_WhenMaxMessageNumberAchieved() {
        ConversationSingleThread conversation = new ConversationSingleThread();
        conversation.addInitiator(initiator);
        conversation.addUser(user);
        MessageLogger logger = (conversation.start(firstMessage));

        assertEquals(20, logger.getSentMessages().size());
        assertEquals(20, logger.getReceivedMessages().size());
    }

}
