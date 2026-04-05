package web.service;
import web.model.User;
import java.util.List;
public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
    User getUserById(long id);
    void updateUser(long id, User updatedUser);
    void deleteUser(long id);
}
