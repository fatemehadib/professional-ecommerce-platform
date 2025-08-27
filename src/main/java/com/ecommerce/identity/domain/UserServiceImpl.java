package com.ecommerce.identity.domain;

import com.ecommerce.identity.dto.RegisterUserRequest;
import com.ecommerce.identity.dto.UserResponse;
import com.ecommerce.identity.infrastructure.UserRepository;
import jakarta.transaction.Transactional;

import java.util.Objects;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserResponse registerUser(RegisterUserRequest registerUserRequest) {


        userRepository.findByEmail(Objects.requireNonNull(registerUserRequest.getEmail())).ifPresent(existingUser -> {
            throw new IllegalArgumentException("User already exists");
        });

        User newUser = new User();
        newUser.setFirstName(registerUserRequest.getFirstName());
        newUser.setLastName(registerUserRequest.getLastName());
        newUser.setEmail(registerUserRequest.getEmail());

        User savedUser = userRepository.save(newUser);

        // 3. Map from the saved Entity (User) to our public DTO (UserResponse)
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setEmail(savedUser.getEmail());
        response.setCreatedAt(savedUser.getCreatedAt());

        return response;
    }
}
