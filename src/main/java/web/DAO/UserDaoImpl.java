package web.DAO;

import org.hibernate.sql.Update;
import org.springframework.stereotype.Repository;
import web.model.User;
import web.service.UserServiceImpl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(long id, User updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Override
    public void deleteUser(long id) {
User user = entityManager.find(User.class,id);
entityManager.remove(user);
    }
}

