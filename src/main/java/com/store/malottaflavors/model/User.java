package com.store.malottaflavors.model;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for Users data table.
 */
@Data
@NoArgsConstructor
@Entity
public class User
{
    @Id
    @NotNull
    private String userId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @Enumerated(value = STRING)
    @NotNull
    private UserRole userRole;

    /**
     * Parameterized {@link User} constructor.
     * @param userId the user's associate ID
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param email the user's email address
     * @param userRole {@link UserRole} The user's role
     */
    public User(String associateId, String firstName, String lastName, String email, UserRole role)
    {
        userId = associateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        userRole = role;
    }

    /**
     * Parameterized {@link User} constructor. Sets default User Role to TRAINEE.
     * @param userId the user's associate ID
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param email the user's email address
     */
    public User(String associateId, String firstName, String lastName, String email)
    {
        this(associateId, firstName, lastName, email, UserRole.CUSTOMER);
    }
}