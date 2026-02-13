package com.pts.user.mapper;

import com.pts.user.entity.User;
import com.pts.common.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    // Entity -> DTO
    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .build();
    }

    // DTO -> Entity
    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(dto.getPassword());
        user.setUserRole(dto.getUserRole());

        return user;
    }

    // List<Entity> -> List<DTO>
    public static List<UserDTO> toDTOList(List<User> users) {
        if (users == null) return null;

        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    // List<DTO> -> List<Entity>
    public static List<User> toEntityList(List<UserDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());
    }
}


