package com.ecommerce.identity.api;


import com.ecommerce.identity.domain.User;
import com.ecommerce.identity.domain.UserService;
import com.ecommerce.identity.dto.RegisterUserRequest;
import com.ecommerce.identity.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserRequest request) {
        UserResponse createdUser = userService.registerUser(request);

        // Return the response DTO with a 201 Created status
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
