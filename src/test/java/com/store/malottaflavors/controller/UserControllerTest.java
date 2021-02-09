package com.store.malottaflavors.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;

import com.store.malottaflavors.json.ErrorApiResponse;
import com.store.malottaflavors.json.MessageApiResponse;
import com.store.malottaflavors.json.UserApiResponse;
import com.store.malottaflavors.json.UserRoleApiResponse;
import com.store.malottaflavors.model.User;
import com.store.malottaflavors.model.UserRole;
import com.store.malottaflavors.repository.UserRepository;

/**
 * Tests {@link UserController}.
 */
public class UserControllerTest
{
    private static final User TEST_USER_ONE = new User("JD012345", "John", "Doe", "JohnDoe@cerner.com");
    private static final User TEST_USER_TWO = new User("ZX987456", "Ray", "Bay", "raybay@cerner.com");
    private static final int REQUEST_CODE_BAD_REQUEST = 400;
    private static final int REQUST_CODE_OK = 200;
    private static final int REQUST_CODE_CREATED = 201;
    private static final int REQUST_CODE_CONFLICT = 409;
    private static final int REQUST_CODE_NOT_FOUND = 404;
    @Mock
    private UserRepository mockUserRepository;
    MockHttpServletResponse mockResponse;
    private UserController userController;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        mockResponse = new MockHttpServletResponse();
        userController = new UserController(mockUserRepository);
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with non alphanumeric
     * characters for user id. Expect the returned {@link ErrorApiResponse} to return correct
     * response.
     */
    @Test
    public void testAddUser_userHasNonAlphaNumericUserId()
    {
        ErrorApiResponse expectedApiResponse = new ErrorApiResponse("Cannot Create User",
                "Only alphanumeric characters allowed in userId, firstName and lastName fields");
        assertEquals(userController.addUser("%^$%", TEST_USER_ONE.getFirstName(), TEST_USER_ONE.getLastName(),
                TEST_USER_ONE.getEmail(), mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with non alphanumeric
     * characters for user id. Expect the response code to be 400.
     */
    @Test
    public void testAddUser_userHasNonAlphaNumericUserIdResponseCodeBadRequest()
    {
        userController.addUser("%^$%", TEST_USER_ONE.getFirstName(), TEST_USER_ONE.getLastName(), TEST_USER_ONE.getEmail(),
                mockResponse);
        mockResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        assertEquals(REQUEST_CODE_BAD_REQUEST, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with non alphanumeric
     * characters for first name. Expect the returned {@link ErrorApiResponse} to return correct
     * response.
     */
    @Test
    public void testAddUser_userHasNonAlphaNumericFirstName()
    {
        ErrorApiResponse expectedApiResponse = new ErrorApiResponse("Cannot Create User",
                "Only alphanumeric characters allowed in userId, firstName and lastName fields");
        assertEquals(userController.addUser(TEST_USER_ONE.getUserId(), "%^$%", TEST_USER_ONE.getLastName(),
                TEST_USER_ONE.getEmail(), mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with non alphanumeric
     * characters for first name. Expect the response code to be 400.
     */
    @Test
    public void testAddUser_userHasNonAlphaNumericFirstNameResponseCodeBadRequest()
    {
        userController.addUser(TEST_USER_ONE.getUserId(), "%^$%", TEST_USER_ONE.getLastName(), TEST_USER_ONE.getEmail(),
                mockResponse);
        mockResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        assertEquals(REQUEST_CODE_BAD_REQUEST, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with non alphanumeric
     * characters for last name. Expect the returned {@link ErrorApiResponse} to return correct
     * response.
     */
    @Test
    public void testAddUser_userHasNonAlphaNumericLastName()
    {
        ErrorApiResponse expectedApiResponse = new ErrorApiResponse("Cannot Create User",
                "Only alphanumeric characters allowed in userId, firstName and lastName fields");
        assertEquals(userController.addUser(TEST_USER_ONE.getUserId(), TEST_USER_ONE.getFirstName(), "%^$%",
                TEST_USER_ONE.getEmail(), mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with non alphanumeric
     * characters for last name. Expect the response code to be 400.
     */
    @Test
    public void testAddUser_userHasNonAlphaNumericLastNameResponseCodeBadRequest()
    {
        userController.addUser(TEST_USER_ONE.getUserId(), TEST_USER_ONE.getFirstName(), "%^$%", TEST_USER_ONE.getEmail(),
                mockResponse);
        mockResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        assertEquals(REQUEST_CODE_BAD_REQUEST, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with blank user id.
     * Expect the returned {@link ErrorApiResponse} to return correct response.
     */
    @Test
    public void testAddUser_userHasBlankUserId()
    {
        ErrorApiResponse expectedApiResponse = new ErrorApiResponse("Cannot Create User", "All fields must not be empty");
        assertEquals(userController.addUser("", TEST_USER_ONE.getFirstName(), TEST_USER_ONE.getLastName(),
                TEST_USER_ONE.getEmail(), mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with blank user id.
     * Expect the response code to be 400.
     */
    @Test
    public void testAddUser_userHasBlankUserIdResponseCodeBadRequest()
    {
        userController.addUser("", TEST_USER_ONE.getFirstName(), TEST_USER_ONE.getLastName(), TEST_USER_ONE.getEmail(),
                mockResponse);
        mockResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        assertEquals(REQUEST_CODE_BAD_REQUEST, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with blank first name.
     * Expect the returned {@link ErrorApiResponse} to return correct response.
     */
    @Test
    public void testAddUser_userHasBlankFirstName()
    {
        ErrorApiResponse expectedApiResponse = new ErrorApiResponse("Cannot Create User", "All fields must not be empty");
        assertEquals(userController.addUser(TEST_USER_ONE.getUserId(), "", TEST_USER_ONE.getLastName(), TEST_USER_ONE.getEmail(),
                mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with blank first name.
     * Expect the response code to be 400.
     */
    @Test
    public void testAddUser_userHasBlankFirstNameResponseCodeBadRequest()
    {
        userController.addUser(TEST_USER_ONE.getUserId(), "", TEST_USER_ONE.getLastName(), TEST_USER_ONE.getEmail(),
                mockResponse);
        mockResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        assertEquals(REQUEST_CODE_BAD_REQUEST, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with blank last name.
     * Expect the returned {@link ErrorApiResponse} to return correct response.
     */
    @Test
    public void testAddUser_userHasBlankLastName()
    {
        ErrorApiResponse expectedApiResponse = new ErrorApiResponse("Cannot Create User", "All fields must not be empty");
        assertEquals(userController.addUser(TEST_USER_ONE.getUserId(), TEST_USER_ONE.getFirstName(), "", TEST_USER_ONE.getEmail(),
                mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with blank last name.
     * Expect the response code to be 400.
     */
    @Test
    public void testAddUser_userHasBlankLastNameResponseCodeBadRequest()
    {
        userController.addUser(TEST_USER_ONE.getUserId(), TEST_USER_ONE.getFirstName(), "", TEST_USER_ONE.getEmail(),
                mockResponse);
        mockResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        assertEquals(REQUEST_CODE_BAD_REQUEST, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with blank email.
     * Expect the returned {@link ErrorApiResponse} to return correct response.
     */
    @Test
    public void testAddNewUser_userHasBlankEmail()
    {
        ErrorApiResponse expectedApiResponse = new ErrorApiResponse("Cannot Create User", "All fields must not be empty");
        assertEquals(userController.addUser(TEST_USER_ONE.getUserId(), TEST_USER_ONE.getFirstName(), TEST_USER_ONE.getLastName(),
                "", mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with blank email.
     * Expect the response code to be 400.
     */
    @Test
    public void testAddUser_userHasBlankEmailResponseCodeBadRequest()
    {
        userController.addUser(TEST_USER_ONE.getUserId(), TEST_USER_ONE.getFirstName(), TEST_USER_ONE.getLastName(), "",
                mockResponse);
        mockResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        assertEquals(REQUEST_CODE_BAD_REQUEST, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with non valid email.
     * Expect the returned {@link ErrorApiResponse} to return correct response.
     */
    @Test
    public void testAddUser_userHasNonValidEmail()
    {
        String testEmail = "NOTANEMAIL";
        ErrorApiResponse expectedApiResponse = new ErrorApiResponse("Cannot Create User", testEmail + " is not a valid email");
        assertEquals(userController.addUser(TEST_USER_ONE.getUserId(), TEST_USER_ONE.getFirstName(), TEST_USER_ONE.getLastName(),
                testEmail, mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with a non valid
     * email. Expect the response code to be 400.
     */
    @Test
    public void testAddUser_userHasNonValidEmailResponseCodeBadRequest()
    {
        userController.addUser(TEST_USER_ONE.getUserId(), TEST_USER_ONE.getFirstName(), TEST_USER_ONE.getLastName(), "NOTANEMAIL",
                mockResponse);
        mockResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        assertEquals(REQUEST_CODE_BAD_REQUEST, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} but user already
     * exists. Expect the returned {@link ErrorApiResponse} to return correct response.
     */
    @Test
    public void testAddNewUser_userAlreadyExists()
    {
        ErrorApiResponse expectedApiResponse = new ErrorApiResponse("Cannot Create User",
                "User with userId " + TEST_USER_ONE.getUserId() + " already exsits");
        Mockito.when(mockUserRepository.existsById(TEST_USER_ONE.getUserId())).thenReturn(true);
        assertEquals(userController.addUser(TEST_USER_ONE.getUserId(), TEST_USER_ONE.getFirstName(), TEST_USER_ONE.getLastName(),
                TEST_USER_ONE.getEmail(), mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} with a user already
     * existing. Expect the response code to be 409.
     */
    @Test
    public void testAddUser_userAlreadyExistsResponseCodeConflict()
    {
        userController.addUser(TEST_USER_ONE.getUserId(), TEST_USER_ONE.getFirstName(), TEST_USER_ONE.getLastName(), "NOTANEMAIL",
                mockResponse);
        mockResponse.setStatus(HttpServletResponse.SC_CONFLICT);
        assertEquals(REQUST_CODE_CONFLICT, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} successfully saving a
     * user to database. Expect the returned {@link MessageApiResponse} to return correct response.
     */
    @Test
    public void testAddNewUser_successfullyAddingUser()
    {
        MessageApiResponse expectedApiResponse = new MessageApiResponse(
                "User with userID: " + TEST_USER_ONE.getUserId() + " was created successfully");
        Mockito.when(mockUserRepository.existsById(TEST_USER_ONE.getUserId())).thenReturn(false);
        Mockito.when(mockUserRepository.save(TEST_USER_ONE)).thenReturn(TEST_USER_ONE);
        assertEquals(userController.addUser(TEST_USER_ONE.getUserId(), TEST_USER_ONE.getFirstName(), TEST_USER_ONE.getLastName(),
                TEST_USER_ONE.getEmail(), mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#addNewUser(String, String, String, String)} successfully saving
     * user. Expect the response code to be 201.
     */
    @Test
    public void testAddUser_successfullyAddingUserResponseCodeCreated()
    {
        userController.addUser(TEST_USER_ONE.getUserId(), TEST_USER_ONE.getFirstName(), TEST_USER_ONE.getLastName(),
                TEST_USER_ONE.getEmail(), mockResponse);
        mockResponse.setStatus(HttpServletResponse.SC_CREATED);
        assertEquals(REQUST_CODE_CREATED, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#getAllUsers()}. Expect the returned {@link UserApiResponse} to
     * return correct response.
     */
    @Test
    public void testGetAllUsers_returnsCorrectUserList()
    {
        ArrayList<User> testUserList = new ArrayList<User>();
        testUserList.add(TEST_USER_ONE);
        testUserList.add(TEST_USER_TWO);
        UserApiResponse<Iterable<User>> expectedApiResponse = new UserApiResponse<Iterable<User>>(testUserList);
        Mockito.when(mockUserRepository.findAll()).thenReturn(testUserList);
        assertEquals(userController.getAllUsers(), expectedApiResponse);
    }

    /**
     * Test {@link UserController#getAllUsers()}. Expect the returned {@link UserApiResponse} to
     * return correct response.
     */
    @Test
    public void testGetAllUsers_returnsEmptyList()
    {
        ArrayList<User> testUserList = new ArrayList<User>();
        UserApiResponse<Iterable<User>> expectedApiResponse = new UserApiResponse<Iterable<User>>(testUserList);
        Mockito.when(mockUserRepository.findAll()).thenReturn(testUserList);
        assertEquals(userController.getAllUsers(), expectedApiResponse);
    }

    /**
     * Test {@link UserController#getUser(String)}. Expect the returned {@link UserApiResponse} to
     * have the correct user.
     */
    @Test
    public void testGetUser_returnCorrectUser()
    {
        Optional<User> testOptionalList = Optional.of(TEST_USER_ONE);
        UserApiResponse<Optional<User>> expectedApiResponse = new UserApiResponse<>(testOptionalList);
        Mockito.when(mockUserRepository.findById(TEST_USER_ONE.getUserId())).thenReturn(testOptionalList);
        assertEquals(userController.getUser(TEST_USER_ONE.getUserId(), mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#getUser(String)}. Expect the {@link HttpServletResponse} to have
     * the code 200.
     */
    @Test
    public void testGetUser_returnCorrectUserResponseCodeOk()
    {
        Optional<User> testOptionalList = Optional.of(TEST_USER_ONE);
        Mockito.when(mockUserRepository.findById(TEST_USER_ONE.getUserId())).thenReturn(testOptionalList);
        mockResponse.setStatus(HttpServletResponse.SC_OK);
        assertEquals(REQUST_CODE_OK, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#getUser(String)}. Expect the returned {@link ErrorApiResponse} to
     * be return correct response.
     */
    @Test
    public void testGetUser_returnsNoUser()
    {
        Optional<User> testOptionalList = Optional.empty();
        ErrorApiResponse expectedApiResponse = new ErrorApiResponse("Could not load user", "User does not exist");
        Mockito.when(mockUserRepository.findById(TEST_USER_ONE.getUserId())).thenReturn(testOptionalList);
        assertEquals(userController.getUser(TEST_USER_ONE.getUserId(), mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#getUser(String)}. Expect the {@link HttpServletResponse} to have
     * the code 404.
     */
    @Test
    public void testGetUser_returnNResponseCodeNotFound()
    {
        Optional<User> testOptionalList = Optional.empty();
        Mockito.when(mockUserRepository.findById(TEST_USER_ONE.getUserId())).thenReturn(testOptionalList);
        mockResponse.setStatus(HttpServletResponse.SC_OK);
        assertEquals(REQUST_CODE_OK, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#deleteUser(String)}. Expect the returned
     * {@link MessageApiResponse} to return correct response.
     */
    @Test
    public void testDeleteUser_deletesUserSuccessfully()
    {
        MessageApiResponse expectedApiResponse = new MessageApiResponse(
                "User with userId " + TEST_USER_ONE.getUserId() + " was deleted");
        Mockito.when(mockUserRepository.existsById(TEST_USER_ONE.getUserId())).thenReturn(true);
        Mockito.doNothing().when(mockUserRepository).deleteById(TEST_USER_ONE.getUserId());
        assertEquals(userController.deleteUser(TEST_USER_ONE.getUserId(), mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#deleteUser(String)}. Expect the returned
     * {@link HttpServletResponse} to have code 200.
     */
    @Test
    public void testDeleteUser_deletesUserSuccessfullyResponseCodeOk()
    {
        Mockito.when(mockUserRepository.existsById(TEST_USER_ONE.getUserId())).thenReturn(true);
        Mockito.doNothing().when(mockUserRepository).deleteById(TEST_USER_ONE.getUserId());
        mockResponse.setStatus(HttpServletResponse.SC_OK);
        assertEquals(REQUST_CODE_OK, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#deleteUser(String)}. Expect the returned {@link ErrorApiResponse}
     * to return correct response.
     */
    @Test
    public void testDeleteUser_noUserToDelete()
    {
        ErrorApiResponse expectedApiResponse = new ErrorApiResponse("Could not delete user",
                "User with userId " + TEST_USER_ONE.getUserId() + " does not exist");
        Mockito.when(mockUserRepository.existsById(TEST_USER_ONE.getUserId())).thenReturn(false);
        assertEquals(userController.deleteUser(TEST_USER_ONE.getUserId(), mockResponse), expectedApiResponse);
    }

    /**
     * Test {@link UserController#deleteUser(String)}. Expect the returned
     * {@link HttpServletResponse} to have code 404.
     */
    @Test
    public void testDeleteUser_noUserToDeleteResponseCodeNotFound()
    {
        Mockito.when(mockUserRepository.existsById(TEST_USER_ONE.getUserId())).thenReturn(false);
        mockResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        assertEquals(REQUST_CODE_NOT_FOUND, mockResponse.getStatus());
    }

    /**
     * Test {@link UserController#getUserRoles()}. Expect the returned {@link UserRoleApiResponse}
     * to return the correct user roles.
     */
    @Test
    public void testGetUserRole_returnsAllUserRolesList()
    {
        List<UserRole> userRoleList = Arrays.asList(UserRole.values());
        UserRoleApiResponse expectedApiResponse = new UserRoleApiResponse(userRoleList);
        assertEquals(userController.getUserRoles(), expectedApiResponse);
    }
}
