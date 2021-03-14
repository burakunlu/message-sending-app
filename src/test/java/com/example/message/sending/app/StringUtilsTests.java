package com.example.message.sending.app;

import com.example.message.sending.app.util.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertThrows;


/**
 * This class consists tests cases of StringUtils class
 *
 * @author Burak Unlu
 */
public class StringUtilsTests {

    /**
     * check behaviour when invalid argument is passed while getting conversation type as command line argument
     *
     * @result throw IllegalArgument error
     */
    @Test
    public void getConversationType_ShouldThrowError_WhenInvalidArgumentPassed() {
        String args[] = {};

        assertThrows(
                IllegalArgumentException.class, () -> {
                    StringUtils.getConversationType(args);
                }
        );
    }

    /**
     * Check behaviour when invalid argument is passed while getting first message as command line argument
     *
     * @result throw IllegalArgument error
     */
    @Test
    public void getFirstMessage_ShouldThrowError_WhenInvalidArgumentPassed() {
        String args[] = {};

        assertThrows(
                IllegalArgumentException.class, () -> {
                    StringUtils.getFirstMessage(args);
                }
        );
    }

    /**
     * Check behaviour when invalid argument is passed while getting user name as command line argument
     *
     * @result throw IllegalArgument error
     */
    @Test
    public void getUserName_ShouldThrowError_WhenInvalidArgumentPassed() {
        String args[] = {};

        assertThrows(
                IllegalArgumentException.class, () -> {
                    StringUtils.getUserName(args);
                }
        );
    }
}
