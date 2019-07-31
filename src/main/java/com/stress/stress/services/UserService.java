package com.stress.stress.services;

import com.stress.stress.domain.User;
import java.util.List;

public interface UserService {
    User findUserById(Long id);
    User findUserByEmail(String email);
    List<User> findAllUsers();
	User saveUser(User user);
}