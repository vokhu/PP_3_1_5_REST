package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserRep;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@org.springframework.transaction.annotation.Transactional(readOnly = true)
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
    public List<User> getAll() {
        return userRep.findAll();
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        User user = userRep.findByUsername(username);
        return user;
    }

    @Override
    public User getUserById(int id) {
        Optional<User> user = userRep.findById(id);
        return user.orElse(null);
    }

    @Override
    @Transactional
    public void updUser(User user, int id) {
        if (!user.getPassword().equals(getUserById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRep.save(user);
    }

    @Override
    @Transactional
    public void deleteUsr(int id) {
        userRep.deleteById(id);
    }

    @Override
    @Transactional
    public void addNewUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRep.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRep.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No registred user" + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user.getAuthorities());
    }

}


