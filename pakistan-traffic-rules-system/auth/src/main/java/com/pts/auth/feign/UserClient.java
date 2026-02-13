package com.pts.auth.feign;


import com.pts.common.dto.ApiResponse;
import com.pts.common.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "user") // this use user-service to internal calls
public interface UserClient {

    // save user
    @PostMapping("/")
    ApiResponse<UserDTO> saveUser(UserDTO userDTO);

    // update user
    @PutMapping("/")
    ApiResponse<UserDTO> updateUser(UserDTO userDTO);

    // get by id
    @PostMapping("/id/{id}")
    ApiResponse<UserDTO> getById(@PathVariable("id") String id);// get by id

    // get by phone or email
    @PostMapping("/username/{username}")
    ApiResponse<UserDTO> getByEmailOrPhoneNumber(@PathVariable("username") String username);


}
