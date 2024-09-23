package com.github.ozelahmet.mockito;

public interface MailService {

    void sendGreetingMail(String mailAddress);

    void sendSpecialGreetingMail(String mailAddress, String additionalInfo);
}
