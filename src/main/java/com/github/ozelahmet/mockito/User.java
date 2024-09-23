package com.github.ozelahmet.mockito;

public class User {
    private final String username;
    private String mailAddress;

    public String getUsername() {
        return username;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public User(String username, String mailAddress) {
        this.username = username;
        this.mailAddress = mailAddress;
    }

    public String getSpecialInfo() throws InterruptedException {
        Thread.sleep(5000);
        return "info";
    }
}
