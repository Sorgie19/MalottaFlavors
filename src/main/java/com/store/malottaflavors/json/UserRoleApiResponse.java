package com.store.malottaflavors.json;

import java.util.List;

import com.store.malottaflavors.model.UserRole;

import lombok.Data;

/**
 * Model for {@link UserRoleApiResponse}.
 */
@Data
public class UserRoleApiResponse
{
    final private List<UserRole> roles;

    /**
     * Parameterized {@link UserRoleApiResponse} constructor.
     * @param role {@link List} of UserRoles
     */
    public UserRoleApiResponse(final List<UserRole> roles)
    {
        this.roles = roles;
    }
}
