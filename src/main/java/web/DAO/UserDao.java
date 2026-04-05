package web.DAO;

import web.model.User;
import java.util.List;


public interface UserDao {
    List<User> getAllUsers();
    void saveUser(User user);
    User getUserById(long id);
    void updateUser(long id, User updatedUser);
    void deleteUser(long id);
}
