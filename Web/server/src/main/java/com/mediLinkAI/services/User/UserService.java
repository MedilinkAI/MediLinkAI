package com.mediLinkAI.services.User;

import com.mediLinkAI.dto.Authentication.LoginDTO;
import com.mediLinkAI.dto.Master.ResponseDTO;
import com.mediLinkAI.dto.User.UserDTO;
import com.mediLinkAI.exception.MediLinkAI;

public interface UserService {
    public UserDTO registerUser(UserDTO userDTO) throws MediLinkAI;

    public UserDTO loginUser(LoginDTO loginDTO) throws MediLinkAI;

    public void sendOtp(String identifier) throws Exception;

    public Boolean verifyOtp(String identifier, String otp) throws MediLinkAI;

    public ResponseDTO changePassword(LoginDTO loginDTO) throws MediLinkAI;
}
