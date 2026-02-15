package com.pts.auth.feign;


import com.pts.common.dto.ApiResponse;
import com.pts.common.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "http://localhost:8086") // this use user-service to internal calls
public interface UserClient {

    // save user
    @PostMapping("/users")
    ApiResponse<UserDTO> saveUser(@RequestBody UserDTO userDTO);

    // update user
    @PutMapping("/users")
    ApiResponse<UserDTO> updateUser(@RequestBody UserDTO userDTO);

    // get by id
    @GetMapping("/users/id/{id}")
    ApiResponse<UserDTO> getById(@PathVariable("id") String id);// get by id

    // get by phone or email
    @GetMapping("/users/username/{username}")
    ApiResponse<UserDTO> getByEmailOrPhoneNumber(@PathVariable("username") String username);


}
