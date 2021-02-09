package com.store.malottaflavors.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.malottaflavors.json.ErrorApiResponse;
import com.store.malottaflavors.json.MessageApiResponse;
import com.store.malottaflavors.json.UserApiResponse;
import com.store.malottaflavors.json.UserRoleApiResponse;
import com.store.malottaflavors.model.User;
import com.store.malottaflavors.model.UserRole;
import com.store.malottaflavors.repository.UserRepository;

/**
 * Handles the HTTP requests made from the front end that is relevant to {@link User}. It maps the
 * requests to appropriate methods in {@link UserRepository}.
 */
@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class UserController
{
    private final UserRepository userRepository;

    /**
     * Parameterized constructor of this {@link UserController}.
     * @param userRepository the {@link UserRepository} of this {@link UserController}
     */
    public UserController(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    /**
     * Add {@link User} in the database. Maps POST Requests for adding a User to /users. Checks to
     * see if UserId, <code>firstName</code> and <code>lastName</code> are alphanumeric characters.
     * Checks to see if any fields are empty. Checks to see email is a valid email. Checks to see
     * user already exists in the database. If all checks pass it saves user to database.
     * @param {@link String} <code>userId</code> is the ID of the {@link User}
     * @param {@link String} <code>firstName</code> is the First name of the {@link User}
     * @param {@link String} <code>lastName</code> is the Last name of the {@link User}
     * @param {@link String} email is the Email the {@link User}
     * @return {@link MessageApiResponse} with confirmation if created successfully. Otherwise
     *         returns {@link ErrorApiResponse} with the error and the message userd.
     */
    @PostMapping(path = "/users")
    public @ResponseBody Object addUser(@NotEmpty @RequestParam String userId, @NotEmpty @RequestParam String firstName, @NotEmpty @RequestParam String lastName, @NotEmpty @RequestParam String email, HttpServletResponse response)
    {
        User user = new User(userId, firstName, lastName, email);
        if(!userId.matches("[a-zA-Z0-9]*") || !firstName.matches("[a-zA-Z0-9]*") || !lastName.matches("[a-zA-Z0-9]*"))
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ErrorApiResponse("Cannot Create User",
                    "Only alphanumeric characters allowed in userId, firstName and lastName fields");
        }
        else if(userId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty())
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ErrorApiResponse("Cannot Create User", "All fields must not be empty");
        }
        else if(!EmailValidator.getInstance().isValid(email))
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ErrorApiResponse("Cannot Create User", email + " is not a valid email");
        }
        else if(userRepository.existsById(userId))
        {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return new ErrorApiResponse("Cannot Create User", "User with userId " + userId + " already exsits");
        }
        userRepository.save(user);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return new MessageApiResponse("User with userID: " + userId + " was created successfully");
    }

    /**
     * Get all {@link User}s in the database. Maps GET request for getting all user to /users.
     * @return {@link UserApiResponse} that contains the {@link Iterable} list of users. Will return
     *         an empty list if no users.
     */
    @GetMapping(path = "/users")
    public @ResponseBody Object getAllUsers()
    {
        return new UserApiResponse<Iterable<User>>(userRepository.findAll());
    }

    /**
     * Get specified {@link User} in the database. Maps GET request for getting a user to
     * /users/{userId}.
     * @param {{@link String}<code>userId</code> <code>userId</code> ID of the {@link User}
     * @return {@link UserApiResponse} that contains the {@link Optional} list of specified user if
     *         the user was found. Otherwise it returns {@link ErrorApiResponse}.
     */
    @GetMapping(path = "/users/{userId}")
    public @ResponseBody Object getUser(@PathVariable String userId, HttpServletResponse response)
    {
        Optional<User> specifiedUser = userRepository.findById(userId);
        if(!specifiedUser.isPresent())
        {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ErrorApiResponse("Could not load user", "User does not exist");
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return new UserApiResponse<Optional<User>>(specifiedUser);
    }

    /**
     * Delete {@link User} in the database. Maps DELETE request for deleting a user by user id to
     * /users/{userId}.
     * @param {@link String} <code>userId</code> ID of the {@link User}
     * @return {@link MessageApiResponse} confirming the deletion of a user or
     *         {@link ErrorApiResponse} if there was an error deleting the user
     */
    @DeleteMapping(path = "/users/{userId}")
    public @ResponseBody Object deleteUser(@PathVariable String userId, HttpServletResponse response)
    {
        if(!userRepository.existsById(userId))
        {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ErrorApiResponse("Could not delete user", "User with userId " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
        response.setStatus(HttpServletResponse.SC_OK);
        return new MessageApiResponse("User with userId " + userId + " was deleted");
    }

    /**
     * Get all {@link List} of UserRoles. Maps GET request for all the UserRole values to
     * /users/roles.
     * @return {@link UserRolApiResponse} that contains a {@link List} of User Roles
     */
    @GetMapping(path = "/users/roles")
    public @ResponseBody UserRoleApiResponse getUserRoles()
    {
        List<UserRole> userRoleList = Arrays.asList(UserRole.values());
        return new UserRoleApiResponse(userRoleList);
    }
}
