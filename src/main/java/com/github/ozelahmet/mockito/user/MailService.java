package com.github.ozelahmet.mockito.user;

public interface MailService {

    void sendGreetingMail(String mailAddress);

    void sendSpecialGreetingMail(String mailAddress, String additionalInfo);
}
