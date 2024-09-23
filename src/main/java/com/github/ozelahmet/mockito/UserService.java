package com.github.ozelahmet.mockito;

public class UserService {

    private final UserRepository userRepository;
    private final MailService mailService;

    public UserService(UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    public boolean greetUser(String username) {
        User user = userRepository.getUser(username);
        if (user == null) {
            return false;
        }

        mailService.sendGreetingMail(user.getMailAddress());
        return true;
    }

    public boolean specialGreetUser(String username) throws InterruptedException {
        User user = userRepository.getUser(username);
        if (user == null) {
            return false;
        }

        mailService.sendSpecialGreetingMail(getSpecialMailAdress(user), user.getSpecialInfo());
        return true;
    }

    /**
     * Normally, this method would be in <code>User</code> class.
     */
    public static String getSpecialMailAdress(User user) {
        return "Dear %s <%s>".formatted(user.getUsername(), user.getMailAddress());
    }

}
