package rest_bank;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
