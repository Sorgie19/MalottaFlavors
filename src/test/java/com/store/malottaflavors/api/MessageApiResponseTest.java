package com.store.malottaflavors.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.store.malottaflavors.json.MessageApiResponse;

/**
 * Tests the {@link MessageApiResponse} model.
 */
public class MessageApiResponseTest
{
    private final String testMessage = "Message";
    private MessageApiResponse testMessageApiResponse;

    @BeforeEach
    public void setUp()
    {
        testMessageApiResponse = new MessageApiResponse(testMessage);
    }

    /**
     * Tests {@link MessageApiResponse} constructor with valid parameters. Expects the
     * {@link MessageApiResponse} object to not be <code>null</code>.
     */
    @Test
    public void testParameterizedUserConstructor_messageApiResponseNotNull()
    {
        assertNotNull(testMessageApiResponse);
    }

    /**
     * Tests {@link MessageApiResponse} constructor with valid parameters. Expects the
     * {@link MessageApiResponse} message property to be set correctly.
     */
    @Test
    public void testParameterizedUserConstructor_messageApiResponseMessageSetCorrectly()
    {
        assertEquals(testMessage, testMessageApiResponse.getMessage());
    }
}
