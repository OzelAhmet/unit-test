package com.github.ozelahmet.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StaticMockTest {

    @Test
    void sendingGreetingMailStaticMock() throws InterruptedException {
        // Arrange
        String username = "test";
        String mailAddress = "test@mail.com";
        User user = new User(username, mailAddress);

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        MailService mailService = Mockito.mock(MailService.class);
        UserService userService = new UserService(userRepository, mailService);

        Mockito.when(userRepository.getUser(username)).thenReturn(user);

        try (MockedStatic<UserService> userServiceMockedStatic = Mockito.mockStatic(UserService.class)) {
            userServiceMockedStatic.when(() -> UserService.getSpecialMailAdress(user)).thenReturn("testMail");

            // Act
            boolean success = userService.specialGreetUser(username);

            // Assert
            assertTrue(success);
            Mockito.verify(mailService).sendSpecialGreetingMail("testMail", "info");
        }

    }
}
