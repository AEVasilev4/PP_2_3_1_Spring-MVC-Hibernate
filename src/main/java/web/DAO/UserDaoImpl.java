package web.DAO;

import org.springframework.stereotype.Repository;
import web.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext // Внедряет EntityManager
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user); // Для сохранения нового объекта
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id); // Для поиска по ID
    }

    @Override
    public void updateUser(long id, User updatedUser) {
        User userToUpdate = getUserById(id); // Сначала находим существующего
        if (userToUpdate != null) {
            userToUpdate.setName(updatedUser.getName());
            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setAge(updatedUser.getAge());
            entityManager.merge(userToUpdate); // Для обновления существующего объекта
        }
    }

    @Override
    public void deleteUser(long id) {
        User userToDelete = getUserById(id);
        if (userToDelete != null) {
            entityManager.remove(userToDelete); // Для удаления
        }
    }
}

