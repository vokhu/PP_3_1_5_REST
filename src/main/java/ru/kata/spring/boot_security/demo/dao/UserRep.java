package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public interface UserRep extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u left join fetch u.roles where u.userName = :username")
    User findUserByName(@Param("username") String username);
}
