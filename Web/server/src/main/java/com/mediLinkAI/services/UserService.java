package com.mediLinkAI.services;

import com.mediLinkAI.dto.LoginDTO;
import com.mediLinkAI.dto.ResponseDTO;
import com.mediLinkAI.dto.UserDTO;
import com.mediLinkAI.exception.MediLinkAI;

public interface UserService {
    public UserDTO registerUser(UserDTO userDTO) throws MediLinkAI;

    public UserDTO loginUser(LoginDTO loginDTO) throws MediLinkAI;

    public void sendOtp(String email) throws Exception;

    public Boolean verifyOtp(String email, String otp) throws MediLinkAI;

    public ResponseDTO changePassword(LoginDTO loginDTO) throws MediLinkAI;
}
