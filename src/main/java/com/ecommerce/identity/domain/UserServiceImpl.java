package com.ecommerce.identity.domain;

import com.ecommerce.identity.infrastructure.UserRepository;
import jakarta.transaction.Transactional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public User registerUser(User user) {

        userRepository.findByEmail(user.getEmail()).ifPresent(existingUser -> {
            throw new IllegalArgumentException("User already exists");
        });

        return userRepository.save(user);
    }
}
