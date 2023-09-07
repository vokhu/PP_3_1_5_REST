package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAll();


    User getUserById(int id);

    void updUser(User upduser);

    void deleteUsr(int id);

    void addNewUser(User newUser);
}
