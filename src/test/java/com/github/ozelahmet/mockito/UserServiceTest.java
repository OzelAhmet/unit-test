package com.github.ozelahmet.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    /**
     * In this test <code>UserRepository</code> and <code>MailService</code> are mocked. <code>UserRepository</code>
     * acts like a stub and <code>MailService</code> acts like a mock here according to "Unit Testing PPP". We used
     * <code>UserRepository</code> to emulate incoming interaction. <code>MailService</code> is used to examine
     * outgoing interaction.
     */
    @Test
    void sendingGreetingMail() {
        // Arrange
        String username = "test";
        String mailAddress = "test@mail.com";
        User user = new User(username, mailAddress);

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        MailService mailService = Mockito.mock(MailService.class);
        UserService userService = new UserService(userRepository, mailService);

        Mockito.when(userRepository.getUser(username)).thenReturn(user);

        // Act
        boolean success = userService.greetUser(username);

        // Assert
        assertTrue(success);
        Mockito.verify(mailService).sendGreetingMail(mailAddress);
        Mockito.verifyNoMoreInteractions(mailService);
    }

    /**
     * <code>never</code> can be used to verify <code>sendGreetingMail</code> method is never called. There are also
     * <code>times</code>, <code>atLeast</code> methods to check number of interactions.
     */
    @Test
    void sendingGreetingMailException() {
        // Arrange
        String username = "test";

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        MailService mailService = Mockito.mock(MailService.class);
        UserService userService = new UserService(userRepository, mailService);

        Mockito.when(userRepository.getUser(username)).thenThrow(IllegalArgumentException.class);

        // Act
        assertThrows(IllegalArgumentException.class, () -> userService.greetUser(username));

        // Assert
        Mockito.verify(mailService, Mockito.never()).sendGreetingMail(Mockito.anyString());
        Mockito.verifyNoInteractions(mailService);
    }

}