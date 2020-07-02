package com.example.demo.service.user;

import com.example.demo.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> findAll();

    User findByUuid(UUID uuid);

    void save(User user);
}
