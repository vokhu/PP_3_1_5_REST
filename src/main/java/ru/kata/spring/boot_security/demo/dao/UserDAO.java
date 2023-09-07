package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();

    User getUserById(int id);

    void updUser(User upduser);

    void deleteUsr(int id);

    void addNewUser(User newUser);
    User getUserByUsername(String username);
}
