package com.pts.common.dto;

import com.pts.common.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO {

    private String accessToken;

    private String refreshToken;   // optional but recommended

    private String tokenType;      // "Bearer"

    private Long expiresIn;        // expiration time in milliseconds

    private String userId;

    private String email;

    private UserRole role;
}

