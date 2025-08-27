package com.ecommerce.identity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password; // We'll handle hashing in the service layer
}