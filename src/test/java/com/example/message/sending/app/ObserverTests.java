package com.example.message.sending.app;

import com.example.message.sending.app.observer.Topic;
import com.example.message.sending.app.model.User;
import com.example.message.sending.app.observer.SubscriberMultiThread;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * This class consists tests cases of Observer Pattern
 *
 * @author Burak Unlu
 */

public class ObserverTests {

    Topic topic;
    SubscriberMultiThread subscriberMultiThread1;
    SubscriberMultiThread subscriberMultiThread2;

    @Before
    public void initObjects() {
        topic = new Topic();
        subscriberMultiThread1 = new SubscriberMultiThread(topic, new User("user"));
        subscriberMultiThread2 = new SubscriberMultiThread(topic, new User("other user"));
    }

    /**
     * Send message to the topic
     *
     * @result messages should be handled by MessageLogger
     */
    @Test
    public void shouldSendMessages() {

        topic.update(subscriberMultiThread1, "message");
        topic.update(subscriberMultiThread2, "another message");

        Assert.assertEquals(2, topic.getMessageLogger().getSentMessages().size());
    }

    /**
     * Receive message from the topic
     *
     * @result messages should be handled by MessageLogger
     */
    @Test
    public void shouldReceiveMessages() {

        topic.update(subscriberMultiThread1, "message");
        topic.update(subscriberMultiThread2, "another message");
        topic.update(subscriberMultiThread2, "a different message");

        Assert.assertEquals(3, topic.getMessageLogger().getReceivedMessages().size());
    }

    /**
     * Check behaviour when invalid message is passed
     *
     * @result throw IllegalArgument error
     */
    @Test
    public void shouldThrowError_WhenInvalidMessageIsReceived() {
        String message = "";
        assertThrows(
                IllegalArgumentException.class, () -> {
                    topic.update(subscriberMultiThread1, message);
                }
        );
    }

}
