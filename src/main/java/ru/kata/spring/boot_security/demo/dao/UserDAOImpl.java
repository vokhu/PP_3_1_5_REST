package ru.kata.spring.boot_security.demo.dao;



import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updUser(User upduser) {
        entityManager.merge(upduser);
    }

    @Override
    public void deleteUsr(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void addNewUser(User newUser) {
        entityManager.persist(newUser);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = entityManager./*find(User.class, username)*/createQuery("SELECT u FROM User u left join fetch u.roleSet where u.userName = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        if(user==null) {
            throw new UsernameNotFoundException("User with login" + username +"doesn't exist.");
        }
        return user;
    }


}
