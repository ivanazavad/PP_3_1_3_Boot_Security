package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(Integer id);
    User getUserByUsername(String username);

    void saveUser(User person);

    void updateUser(Integer id, User person);

    void deleteUser(Integer id);

}
