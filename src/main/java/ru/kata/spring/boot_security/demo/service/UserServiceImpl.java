package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void updUser(User upduser) {
        userDAO.updUser(upduser);
    }

    @Override
    @Transactional
    public void deleteUsr(int id) {
        userDAO.deleteUsr(id);
    }

    @Override
    @Transactional
    public void addNewUser(User newUser) {
        userDAO.addNewUser(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(username);
        return user;
    }
}


