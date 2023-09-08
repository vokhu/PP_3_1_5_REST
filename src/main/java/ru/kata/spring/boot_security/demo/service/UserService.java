package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface UserService /*extends UserDetailsService*/ {

    List<User> getAll();

    User findByUsername(String username);

    User getUserById(int id);

    void updUser(User upduser);

    void deleteUsr(int id);

    void addNewUser(User newUser);

   // Set<Role> getRoles(String... roles);


    Set<Role> getSetRoles(String[] roles);
}
