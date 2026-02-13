package com.pts.common.dto;

import com.pts.common.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String id;

    private String name;

    private String phoneNumber;

    private String email;

    private String password;

    private UserRole userRole = UserRole.CITIZEN;
}
