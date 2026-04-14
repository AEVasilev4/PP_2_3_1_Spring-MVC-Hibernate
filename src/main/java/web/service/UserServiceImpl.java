package web.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void updateUser(long id, User updatedUser) {

        User userToUpdate = getUserById(id);
        if (userToUpdate == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userToUpdate.setName(updatedUser.getName());
        userToUpdate.setEmail(updatedUser.getEmail());
        userToUpdate.setAge(updatedUser.getAge());
        userDao.updateUser(id, updatedUser);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {

        User userToDelete = getUserById(id);
        if (userToDelete != null) {
            userDao.deleteUser(id);
        }
    }
}