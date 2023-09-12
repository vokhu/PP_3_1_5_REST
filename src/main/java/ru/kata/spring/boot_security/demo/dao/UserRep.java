package ru.kata.spring.boot_security.demo.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public interface UserDAOImpl extends JpaRepository <User, Integer> {

    @Query("SELECT u FROM User u left join fetch u.roles where u.userName = :username")
    public User findByUsername(@Param("username") String username);


   /* @PersistenceContext
    private EntityManager entityManager;
*/

/*    @Override
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
    public User findByUsername(String username) {
        User user = entityManager
                .createQuery("SELECT u FROM User u left join fetch u.roles where u.userName = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        if(user==null) {
            throw new UsernameNotFoundException("User with login" + username +"doesn't exist.");
        }
        return user;
    }

    public Set<Role> getSetRoles(String... roles) {
        Set<Role> roleSet;
        roleSet = entityManager
                .createQuery("SELECT role FROM Role role WHERE role.roleName IN (:roles)"
                        , Role.class)
                .setParameter("roles", Arrays.asList(roles))
                .getResultStream().collect(Collectors.toSet());
        return roleSet;
    }*/

}
