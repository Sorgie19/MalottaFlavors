package com.store.malottaflavors.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.store.malottaflavors.json.ErrorApiResponse;

/**
 * Tests the {@link ErrorApiResponse} model.
 */
public class ErrorApiResponseTest
{
    private final String testError = "Error";
    private final String testMessage = "Message";
    private ErrorApiResponse testErrorApiResponse;

    @BeforeEach
    public void setUp()
    {
        testErrorApiResponse = new ErrorApiResponse(testError, testMessage);
    }

    /**
     * Tests {@link ErrorApiResponse} constructor with valid parameters. Expects the
     * {@link ErrorApiResponse} object to not be <code>null</code>.
     */
    @Test
    public void testParameterizedUserConstructor_errorApiResponseNotNull()
    {
        assertNotNull(testErrorApiResponse);
    }

    /**
     * Tests {@link ErrorApiResponse} constructor with valid parameters. Expects the
     * {@link ErrorApiResponse} error property to be set correctly.
     */
    @Test
    public void testParameterizedUserConstructor_errorApiResponseErrorSetCorrectly()
    {
        assertEquals(testError, testErrorApiResponse.getError());
    }

    /**
     * Tests {@link ErrorApiResponse} constructor with valid parameters. Expects the
     * {@link ErrorApiResponse} message property to be set correctly.
     */
    @Test
    public void testParameterizedUserConstructor_errorApiResponseMessageSetCorrectly()
    {
        assertEquals(testMessage, testErrorApiResponse.getMessage());
    }
}
