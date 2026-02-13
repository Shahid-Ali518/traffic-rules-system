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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserClient userClient;


    // method to register user
    public ApiResponse<UserDTO> register(UserDTO userDTO){

        // set password
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        // set role
        userDTO.setUserRole(UserRole.CITIZEN);

        // call feign client to save user
        return userClient.saveUser(userDTO);
    }

    // method to login user
    public AuthResponseDTO login(AuthRequestDTO request){

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

        // return auth response
        return AuthResponseDTO.builder()
                .accessToken(token)
                .userId(userDTO.getId())
                .role(userDTO.getUserRole())
                .build();
    }

}
