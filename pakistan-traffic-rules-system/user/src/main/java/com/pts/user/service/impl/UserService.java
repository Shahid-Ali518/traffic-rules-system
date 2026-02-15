package com.pts.user.service.impl;

import com.pts.common.dto.ApiResponse;
import com.pts.common.dto.UserDTO;
import com.pts.common.exception.DuplicateResourceException;
import com.pts.common.exception.ResourceNotFoundException;
import com.pts.user.entity.User;
import com.pts.user.mapper.UserMapper;
import com.pts.user.repository.UserRepository;
import com.pts.user.service.interfac.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;




    @Override
    public ApiResponse<UserDTO> saveUser(UserDTO user) {


        // check duplicate email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists");
        }
        // check duplicate phone number
        if(userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent())
            throw new DuplicateResourceException("Phone Number already exists");

        User savedUser = userRepository.save(UserMapper.toEntity(user));


        return ApiResponse.<UserDTO>builder()
                .success(true)
                .message("User created successfully")
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .data(UserMapper.toDTO(savedUser))
                .build();
    }

    @Override
    public ApiResponse<UserDTO> updateUser(UserDTO user) {

        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());

        User updatedUser = userRepository.save(existingUser);

        return ApiResponse.<UserDTO>builder()
                .success(true)
                .message("User updated successfully")
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(UserMapper.toDTO(updatedUser))
                .build();
    }

    @Override
    public ApiResponse<UserDTO> findByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with phone number"));

        return ApiResponse.<UserDTO>builder()
                .success(true)
                .message("User fetched successfully")
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(UserMapper.toDTO(user))
                .build();
    }

    @Override
    public ApiResponse<UserDTO> findByEmailOrPhoneNumber(String email, String phoneNumber) {
        User user = userRepository.findByEmailOrPhoneNumber(email, phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return ApiResponse.<UserDTO>builder()
                .message("User fetch successfully")
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(UserMapper.toDTO(user))
                .build();
    }

    @Override
    public ApiResponse<UserDTO> findById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return ApiResponse.<UserDTO>builder()
                .success(true)
                .message("User fetched successfully")
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(UserMapper.toDTO(user))
                .build();
    }

    @Override
    public ApiResponse<UserDTO> findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email"));

        return ApiResponse.<UserDTO>builder()
                .success(true)
                .message("User fetched successfully")
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(UserMapper.toDTO(user))
                .build();
    }

    @Override
    public ApiResponse<UserDTO> deleteUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);

        return ApiResponse.<UserDTO>builder()
                .success(true)
                .message("User deleted successfully")
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(UserMapper.toDTO(user))  // return deleted user info if needed
                .build();
    }
}
