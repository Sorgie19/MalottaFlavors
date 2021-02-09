package com.store.malottaflavors.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.store.malottaflavors.json.UserApiResponse;
import com.store.malottaflavors.model.User;

public class UserApiResponseTest
{
    private User testUser;
    private static final String TEST_USER_ID = "JD123456";
    private static final String TEST_FIRST_NAME = "John";
    private static final String TEST_LAST_NAME = "Doe";
    private static final String TEST_EMAIL = "JohnDoe@Cerner.com";

    @BeforeEach
    public void setUp()
    {
        testUser = new User(TEST_USER_ID, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL);
    }

    @AfterEach
    public void tearDown()
    {
        testUser = null;
    }

    /**
     * Tests {@link UserApiResponse} constructor with valid parameters. Expects the
     * {@link UserApiResponse} object to not be <code>null</code>.
     */
    @Test
    public void testParameterizedUserConstructor_userApiResponseNotNull()
    {
        UserApiResponse<Iterable<User>> testUserApiResponse = new UserApiResponse<Iterable<User>>(testUser);
        assertNotNull(testUserApiResponse);
    }

    /**
     * Tests {@link UserApiResponse} constructor with valid parameters. Expects the
     * {@link UserApiResponse} object to be set correctly.
     */
    @Test
    public void testParameterizedUserConstructor_userApiResponseObjectSetCorrectly()
    {
        UserApiResponse<Iterable<User>> testUserApiResponse = new UserApiResponse<Iterable<User>>(testUser);
        assertEquals(testUser, testUserApiResponse.getUsers());
    }
}
