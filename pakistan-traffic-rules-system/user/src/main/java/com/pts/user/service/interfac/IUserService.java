package com.pts.user.service.interfac;

import com.pts.common.dto.ApiResponse;
import com.pts.common.dto.UserDTO;
import org.springframework.stereotype.Service;


@Service
public interface IUserService {

    ApiResponse<UserDTO> saveUser(UserDTO user);

    ApiResponse<UserDTO> updateUser(UserDTO user);

    ApiResponse<UserDTO> findByPhoneNumber(String phoneNumber);

    ApiResponse<UserDTO> findByEmailOrPhoneNumber(String email, String phoneNumber);


    ApiResponse<UserDTO> findById(String id);

    ApiResponse<UserDTO> findByEmail(String email);

    ApiResponse<UserDTO> deleteUser(String id);


}
