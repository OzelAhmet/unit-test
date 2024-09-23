package com.github.ozelahmet.mockito.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Annotations can also be used to mock dependencies in tests. This method can be useful if mocked dependencies have
 * private access in the class under test. Isolation between test should be considered while using these arguments.
 * <p>
 * Note: To use <code>MockitoExtension</code> class, <code>mockito-junit-jupiter</code> must be added.
 */
@ExtendWith(MockitoExtension.class)
class AnnotationTest {

    private final String username = "test";
    private final String mailAddress = "test@mail.com";

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MailService mailService;

    @Spy
    private User user = new User(username, mailAddress);

    @Captor
    private ArgumentCaptor<String> captor;

    @Test
    void sendingGreetingMail() throws InterruptedException {
        // Arrange
        when(userRepository.getUser(username)).thenReturn(user);
        doReturn("testInfo").when(user).getSpecialInfo();

        // Act
        boolean success = userService.specialGreetUser(username);

        // Assert
        assertTrue(success);
        verify(mailService).sendSpecialGreetingMail(contains("<" + mailAddress + ">"), captor.capture());
        assertEquals("testInfo", captor.getValue());
    }
}