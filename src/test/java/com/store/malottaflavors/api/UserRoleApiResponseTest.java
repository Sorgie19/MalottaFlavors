package com.store.malottaflavors.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.store.malottaflavors.json.UserRoleApiResponse;
import com.store.malottaflavors.model.UserRole;

/**
 * Model for {@link UserRoleApiResponse}.
 */
public class UserRoleApiResponseTest
{
    List<UserRole> testUserRoleList = Arrays.asList(UserRole.values());

    /**
     * Tests {@link UserRoleApiResponse} constructor with valid parameters. Expects the
     * {@link UserRoleApiResponse} object to not be <code>null</code>.
     */
    @Test
    public void testParameterizedUserRoleApiResponseConstructor_messageApiResponseNotNull()
    {
        UserRoleApiResponse testUserRoleApiResponse = new UserRoleApiResponse(testUserRoleList);
        assertNotNull(testUserRoleApiResponse);
    }

    /**
     * Tests {@link UserRoleApiResponse} constructor with valid parameters. Expects the
     * {@link UserRoleApiResponse} userRole list to be set correctly.
     */
    @Test
    public void testParameterizedUserRoleApiResponseConstructor_messageApiResponseUserRoleListSetCorrectly()
    {
        UserRoleApiResponse testUserRoleApiResponse = new UserRoleApiResponse(testUserRoleList);
        assertEquals(testUserRoleList, testUserRoleApiResponse.getRoles());
    }
}
