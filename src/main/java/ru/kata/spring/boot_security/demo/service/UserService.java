package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> allUsers();

    User findByUsername(String username);

    User getUserById(Integer id);

    void update(User user);

    void deleteUser(Integer id);

    User addUsers(User newUser);

}
