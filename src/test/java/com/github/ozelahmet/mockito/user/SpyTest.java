package com.github.ozelahmet.mockito.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SpyTest {

    /**
     * <code>doReturn</code> must be used to prevent actual method to be called.
     * <p>
     * If mock is used here instead of spy, it would fail because <code>User.getUsername</code> and
     * <code>User.getMailAddress</code> would retrun null in <code>getSpecialMailAdress</code>.
     */
    @Test
    void sendingGreetingMail() throws InterruptedException {
        // Arrange
        String username = "test";
        String mailAddress = "test@mail.com";
        User user = spy(new User(username, mailAddress));

        UserRepository userRepository = mock(UserRepository.class);
        MailService mailService = mock(MailService.class);
        UserService userService = new UserService(userRepository, mailService);

        when(userRepository.getUser(username)).thenReturn(user);
        doReturn("testInfo").when(user).getSpecialInfo();

        // Act
        boolean success = userService.specialGreetUser(username);

        // Assert
        assertTrue(success);
        verify(mailService).sendSpecialGreetingMail(contains("<" + mailAddress + ">"), eq("testInfo"));
    }
}
