package com.pts.auth.controller;


import com.pts.auth.service.AuthService;
import com.pts.common.dto.ApiResponse;
import com.pts.common.dto.AuthRequestDTO;
import com.pts.common.dto.AuthResponseDTO;
import com.pts.common.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody UserDTO userDTO){
        ApiResponse<UserDTO> response = authService.register(userDTO);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> login(@RequestBody AuthRequestDTO authRequest){

        ApiResponse<AuthResponseDTO> response = authService.login(authRequest);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


}
