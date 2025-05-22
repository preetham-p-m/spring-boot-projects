package com.pmp.filters_interceptors.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pmp.filters_interceptors.model.User;
import com.pmp.filters_interceptors.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByNameContaining(name);
    }

    public User updateUser(Long id, User user) {
        return userRepository.findById(id).map(existing -> {
            existing.setName(user.getName());
            existing.setEmail(user.getEmail());
            return userRepository.save(existing);
        }).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
