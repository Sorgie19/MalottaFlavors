package com.store.malottaflavors.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.store.malottaflavors.model.User;
import com.store.malottaflavors.model.UserRole;

/**
 * Tests the {@link User} model.
 */
public class UserTest
{
    private User testUser;
    private User testUser_roleSet;
    private static final String TEST_USER_ID = "13251";
    private static final String TEST_FIRST_NAME = "John";
    private static final String TEST_LAST_NAME = "Doe";
    private static final String TEST_EMAIL = "JohnDoe@TEST.com";
    private static final UserRole TEST_USER_ROLE = UserRole.CUSTOMER;
    private static final UserRole TEST_USER_ROLE_WHEN_ROLE_SET = UserRole.ADMINISTRATOR;

    @BeforeEach
    public void initilize()
    {
        testUser = new User(TEST_USER_ID, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL);
        testUser_roleSet = new User(TEST_USER_ID, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL, TEST_USER_ROLE_WHEN_ROLE_SET);
    }

    @AfterEach
    public void tearDown()
    {
        testUser = null;
        testUser_roleSet = null;
    }

    /**
     * Tests {@link User} constructor with valid parameters. Expects the user object to not be
     * <code>null</code>.
     */
    @Test
    public void testParameterizedUserConstructor_userNotNull()
    {
        assertNotNull(testUser);
    }

    /**
     * Tests {@link User} constructor with valid parameters. Expect the associate id field to be set
     * correctly.
     */
    @Test
    public void testParameterizedUserConstructor_setsAssociateIdFieldCorrectly()
    {
        assertEquals(TEST_USER_ID, testUser.getUserId());
    }

    /**
     * Tests {@link User} constructor with valid parameters. Expect the first name field be set
     * correctly.
     */
    @Test
    public void testParameterizedUserConstructor_setsFirstNameFieldCorrectly()
    {
        assertEquals(TEST_FIRST_NAME, testUser.getFirstName());
    }

    /**
     * Tests {@link User} constructor with valid parameters. Expect the last name field to be set
     * correctly.
     */
    @Test
    public void testParameterizedUserConstructor_setsLastNameFieldCorrectly()
    {
        assertEquals(TEST_LAST_NAME, testUser.getLastName());
    }

    /**
     * Tests {@link User} constructor with valid parameters. Expect the email field to be set
     * correctly.
     */
    @Test
    public void testParameterizedUserConstructor_setsEmailNameFieldCorrectly()
    {
        assertEquals(TEST_EMAIL, testUser.getEmail());
    }

    /**
     * Tests {@link User} constructor with valid parameters. Expect the user role field to be set
     * correctly.
     */
    @Test
    public void testParameterizedUserConstructor_setsUserRoleFieldCorrectly()
    {
        assertEquals(TEST_USER_ROLE, testUser.getUserRole());
    }

    /**
     * Tests {@link User} constructor no parameters. Expect the user object to not be
     * <code>null</code>.
     */
    @Test
    public void testNoArgConstructor_userNotNull()
    {
        testUser = new User();
        assertNotNull(testUser);
    }

    /**
     * Tests {@link User} constructor no parameters. Expects setter to set associate id correctly.
     */
    @Test
    public void testSetter_setsAssociateIdFieldCorrectly()
    {
        testUser = new User();
        testUser.setUserId(TEST_USER_ID);
        assertEquals(TEST_USER_ID, testUser.getUserId());
    }

    /**
     * Tests {@link User} constructor no parameters. Expects setter to set first name correctly.
     */
    @Test
    public void testSetter_setsFirstNameCorrectly()
    {
        testUser = new User();
        testUser.setFirstName(TEST_FIRST_NAME);
        assertEquals(TEST_FIRST_NAME, testUser.getFirstName());
    }

    /**
     * Tests {@link User} constructor no parameters. Expects setter to set last night correctly.
     */
    @Test
    public void testSetter_setsLastNameCorrectly()
    {
        testUser = new User();
        testUser.setLastName(TEST_LAST_NAME);
        assertEquals(TEST_LAST_NAME, testUser.getLastName());
    }

    /**
     * Tests {@link User} constructor no parameters. Expects setter to set email correctly.
     */
    @Test
    public void testSetter_setsEmailCorrectly()
    {
        testUser = new User();
        testUser.setEmail(TEST_EMAIL);
        assertEquals(TEST_EMAIL, testUser.getEmail());
    }

    /**
     * Tests {@link User} constructor no parameters. Expects setter to set user role correctly.
     */
    @Test
    public void testSetter_setsUserRoleCorrectly()
    {
        testUser = new User();
        testUser.setUserRole(TEST_USER_ROLE);
        assertEquals(TEST_USER_ROLE, testUser.getUserRole());
    }

    /**
     * Tests {@link User} constructor with valid parameters. Expects the user object to not be
     * <code>null</code>.
     */
    @Test
    public void testParameterizedUserConstructorSettingRole_userNotNull()
    {
        assertNotNull(testUser_roleSet);
    }

    /**
     * Tests {@link User} constructor with valid parameters. Expect the associate id field to be set
     * correctly.
     */
    @Test
    public void testParameterizedUserConstructorSettingRole_setsAssociateIdFieldCorrectly()
    {
        assertEquals(TEST_USER_ID, testUser_roleSet.getUserId());
    }

    /**
     * Tests {@link User} constructor with valid parameters. Expect the first name field be set
     * correctly.
     */
    @Test
    public void testParameterizedUserConstructorSettingRole_setsFirstNameFieldCorrectly()
    {
        assertEquals(TEST_FIRST_NAME, testUser_roleSet.getFirstName());
    }

    /**
     * Tests {@link User} constructor with valid parameters. Expect the last name field to be set
     * correctly.
     */
    @Test
    public void testParameterizedUserConstructorSettingRole_setsLastNameFieldCorrectly()
    {
        assertEquals(TEST_LAST_NAME, testUser_roleSet.getLastName());
    }

    /**
     * Tests {@link User} constructor with valid parameters. Expect the email field to be set
     * correctly.
     */
    @Test
    public void testParameterizedUserConstructorSettingRole_setsEmailNameFieldCorrectly()
    {
        assertEquals(TEST_EMAIL, testUser_roleSet.getEmail());
    }

    /**
     * Tests {@link User} constructor with valid parameters. Expect the user role field to be set
     * correctly.
     */
    @Test
    public void testParameterizedUserConstructorSettingRole_setsUserRoleFieldCorrectly()
    {
        assertEquals(TEST_USER_ROLE_WHEN_ROLE_SET, testUser_roleSet.getUserRole());
    }
}
