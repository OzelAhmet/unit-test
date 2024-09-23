package com.github.ozelahmet.mockito.user;

import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ArgumentTest {

    /**
     * Matchers like <code>anyString</code> can be useful with <code>never</code>. They check the mocked method is never
     * called with any string parameter. They can also be used in <code>when</code> segments.
     */
    @Test
    void sendingGreetingMailMatcher() throws InterruptedException {
        // Arrange
        String username = "test";
        String mailAddress = "test@mail.com";
        User user = spy(new User(username, mailAddress));

        UserRepository userRepository = mock(UserRepository.class);
        MailService mailService = mock(MailService.class);
        UserService userService = new UserService(userRepository, mailService);

        when(userRepository.getUser(anyString())).thenReturn(user);
        doReturn("testInfo").when(user).getSpecialInfo();

        // Act
        boolean success = userService.specialGreetUser(username);

        // Assert
        assertTrue(success);
        verify(mailService).sendSpecialGreetingMail("Dear test <" + mailAddress + ">", "testInfo");
        verify(mailService).sendSpecialGreetingMail(contains(mailAddress), contains("test"));
        verify(mailService).sendSpecialGreetingMail(contains(mailAddress), AdditionalMatchers.and(startsWith("test"), endsWith("Info")));
        verify(mailService).sendSpecialGreetingMail(contains(mailAddress), argThat(arg -> arg.length() == 8));

        verify(mailService, never()).sendGreetingMail(anyString());
    }

    /**
     * In this test, argument captor is used while verifying the interaction.
     * <p>
     * <code>ArgumentCaptor.captor()</code> returns a matcher like <code>Mockito.any()</code>. Therefore, captors can be used
     * instead of matchers.
     */
    @Test
    void sendingGreetingMailCaptorVerify() {
        // Arrange
        String username = "test";
        String mailAddress = "test@mail.com";
        User user = new User(username, mailAddress);

        UserRepository userRepository = mock(UserRepository.class);
        MailService mailService = mock(MailService.class);
        UserService userService = new UserService(userRepository, mailService);

        when(userRepository.getUser(username)).thenReturn(user);

        // Act
        boolean success = userService.greetUser(username);

        // Assert
        assertTrue(success);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mailService).sendGreetingMail(captor.capture());

        assertEquals(mailAddress, captor.getValue());
    }

    /**
     * In this test, argument captor is used while stubbing the interaction. This is not recommended by Mockito. Captors
     * are suggested to be used with <code>verify</code>.
     */
    @Test
    void sendingGreetingMailCaptorWhen() {
        // Arrange
        String username = "test";
        String mailAddress = "test@mail.com";
        User user = new User(username, mailAddress);

        UserRepository userRepository = mock(UserRepository.class);
        MailService mailService = mock(MailService.class);
        UserService userService = new UserService(userRepository, mailService);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        when(userRepository.getUser(captor.capture())).thenReturn(user);

        // Act
        boolean success = userService.greetUser(username);

        // Assert
        assertTrue(success);
        verify(mailService).sendGreetingMail(mailAddress);
        assertEquals(username, captor.getValue());
    }
}
