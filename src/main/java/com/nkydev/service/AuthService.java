package com.nkydev.service;

import com.nkydev.dto.auth.AuthRequestDTO;
import com.nkydev.dto.auth.AuthResponseDTO;
import com.nkydev.dto.user.UserRequestDTO;
import com.nkydev.dto.user.UserResponseDTO;

public interface AuthService {
    UserResponseDTO register(UserRequestDTO request);
    AuthResponseDTO login(AuthRequestDTO request);
}