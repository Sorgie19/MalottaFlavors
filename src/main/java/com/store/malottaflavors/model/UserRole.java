package com.store.malottaflavors.model;

/**
 * The enumeration for a {@link User}'s type.
 */
public enum UserRole
{
    /**
     * TRAINEE: The default user role.
     */
    CUSTOMER,
    /**
     * MANAGER: The manager role; can update products and listings.
     */
    MANAGER,
    /**
     * ADMINSTRATOR: The administrator role; Has full access to the everything.
     */
    ADMINISTRATOR;
}
