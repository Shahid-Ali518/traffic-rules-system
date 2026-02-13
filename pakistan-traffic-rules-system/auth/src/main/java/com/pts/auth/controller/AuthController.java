package com.pts.auth.controller;


import com.pts.common.dto.ApiResponse;
import com.pts.common.dto.AuthRequestDTO;
import com.pts.common.dto.AuthResponseDTO;
import com.pts.common.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping // no need because auth is registered as service discovery
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody UserDTO userDTO){

        return null;
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDTO>> login(@RequestBody AuthRequestDTO authRequest){

        return null;
    }


}
