package com.pts.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private int statusCode;

    T data;

    private LocalDateTime timestamp;
}
