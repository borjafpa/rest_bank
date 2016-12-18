package rest_bank;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
