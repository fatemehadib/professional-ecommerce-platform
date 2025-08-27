package com.ecommerce.identity.domain;

import com.ecommerce.identity.dto.RegisterUserRequest;
import com.ecommerce.identity.dto.UserResponse;

public interface UserService {

    UserResponse registerUser(RegisterUserRequest registerUserRequest);
}
