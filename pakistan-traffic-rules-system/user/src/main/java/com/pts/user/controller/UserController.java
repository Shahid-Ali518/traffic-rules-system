package com.pts.user.controller;


import com.pts.common.dto.ApiResponse;
import com.pts.common.dto.UserDTO;
import com.pts.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // endpoint to save userinfo
    @PostMapping("/")
    public ResponseEntity<ApiResponse<UserDTO>> saveUser(UserDTO userDTO){
        ApiResponse<UserDTO> response = userService.saveUser(userDTO);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    // endpoint to update userinfo
    @PutMapping("/")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(UserDTO userDTO){
        ApiResponse<UserDTO> response = userService.updateUser(userDTO);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    // endpoint to get user by phone number
    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserByPhoneNumber(@PathVariable  String phoneNumber){
        ApiResponse<UserDTO> response = userService.findByPhoneNumber(phoneNumber);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    // endpoint to get user by email or phone number
    @GetMapping("/username/{username}") // username may be email or phone
    public ResponseEntity<ApiResponse<UserDTO>> getUserByEmailOrPhoneNumber( @PathVariable String username){
        ApiResponse<UserDTO> response = userService.findByEmailOrPhoneNumber(username, username);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    // endpoint to get user by id
    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable String id){
        ApiResponse<UserDTO> response = userService.findById(id);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    // endpoint to get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserByEmail( @PathVariable  String email){
        ApiResponse<UserDTO> response = userService.findByEmail(email);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    // endpoint to delete user by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> deleteById(@PathVariable String id){
        ApiResponse<UserDTO> response = userService.deleteUser(id);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }


}
