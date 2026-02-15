package com.pts.auth.service;


import com.pts.auth.feign.UserClient;
import com.pts.common.dto.ApiResponse;
import com.pts.common.dto.AuthRequestDTO;
import com.pts.common.dto.AuthResponseDTO;
import com.pts.common.dto.UserDTO;
import com.pts.common.enums.UserRole;
import com.pts.common.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserClient userClient;


    // method to register user
    public ApiResponse<UserDTO> register(UserDTO userDTO){

        System.out.println(userDTO);

        // set password
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        // set role
        userDTO.setUserRole(UserRole.CITIZEN);

        // call feign client to save user
        return userClient.saveUser(userDTO);
    }

    // method to login user
    public ApiResponse<AuthResponseDTO> login(AuthRequestDTO request){

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();

        // first fetch user details
        UserDTO userDTO = userClient.getByEmailOrPhoneNumber(request.getUsername()).getData();

        // check login method
        String loginMethod;
        if (request.getUsername().equals(userDTO.getEmail())) {
            loginMethod = "EMAIL";
        } else if (request.getUsername().equals(userDTO.getPhoneNumber())) {
            loginMethod = "PHONE";
        } else {
            loginMethod = "UNKNOWN";
        }
        var token = jwtUtil.createToken(userDTO, loginMethod);

        authResponseDTO.setAccessToken(token);
        authResponseDTO.setRole(userDTO.getUserRole());
        authResponseDTO.setUserId(userDTO.getId());


        // return auth response
        return ApiResponse.<AuthResponseDTO>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .message("User logged in successfully")
                .timestamp(LocalDateTime.now())
                .data(authResponseDTO)
                .build();
    }

}
