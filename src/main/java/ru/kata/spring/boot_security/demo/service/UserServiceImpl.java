package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserRep;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@org.springframework.transaction.annotation.Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRep userRep;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRep userRep, PasswordEncoder passwordEncoder) {
        this.userRep = userRep;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> allUsers() {
        return userRep.findAll();
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        User user = userRep.findUserByName(username);
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        return userRep.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id " + id));
        /*Optional<User> user = userRep.findById(id);
        return user.orElseThrow(() ->
                new UsernameNotFoundException("User not found with id " + id));*/
    }

    @Override
    @Transactional
    public void update(User user) {
        if (!user.getPassword().equals(getUserById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRep.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userRep.deleteById(id);
    }

    @Override
    @Transactional
    public User addUsers(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRep.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRep.findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("No registred user" + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user.getRoles());
    }
}


