package com.store.malottaflavors.model;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.sun.istack.NotNull;

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
     * @param userId {@link String} The user's associate ID
     * @param firstName {@link String} The user's first name
     * @param lastName {@link String} The user's last name
     * @param email {@link String} The user's email address
     */
    public User(String userId, String firstName, String lastName, String email)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        userRole = UserRole.CUSTOMER;
    }

}
