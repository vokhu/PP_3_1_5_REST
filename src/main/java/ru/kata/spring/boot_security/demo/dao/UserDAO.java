package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {
    List<User> getAll();

    User getUserById(int id);

    void updUser(User upduser);

    void deleteUsr(int id);

    void addNewUser(User nUser);

    Set<Role> getSetRoles(String[] roles);

    User findByUsername(String username);
}
