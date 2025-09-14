package com.ecommerce.identity.api;

import com.ecommerce.identity.domain.UserService;
import com.ecommerce.identity.dto.RegisterUserRequest;
import com.ecommerce.identity.dto.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    UserService userService;

    @Test
    void testRegisterNewUser() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("new.user@example.com");
        request.setFirstName("New");
        request.setLastName("User");
        request.setPassword("secret");

        UserResponse savedUser = new UserResponse();
        savedUser.setId(1L);
        savedUser.setEmail("new.user@example.com");

        // We use Mockito to define what should happen when the service is called
        given(userService.registerUser(any(RegisterUserRequest.class))).willReturn(savedUser);

        mockMvc.perform(post(UserController.USER_PATH_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

}
