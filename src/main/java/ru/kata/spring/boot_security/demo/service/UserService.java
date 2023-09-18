package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User findByUsername(String username);

    User getUserById(int id);

    void updUser(User user, int id);

    void deleteUsr(int id);

    void addNewUser(User newUser);

}
