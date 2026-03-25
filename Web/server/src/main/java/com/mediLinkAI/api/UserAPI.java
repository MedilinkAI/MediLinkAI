package com.mediLinkAI.api;

import jakarta.validation.Valid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mediLinkAI.dto.Authentication.LoginDTO;
import com.mediLinkAI.dto.Master.ResponseDTO;
import com.mediLinkAI.dto.User.UserDTO;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.services.User.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserAPI {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws MediLinkAI {
        userDTO = userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<UserDTO> loginUser(@RequestBody @Valid LoginDTO loginDTO) throws MediLinkAI {
        UserDTO userDTO = userService.loginUser(loginDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/sendOtp/{identifier}", produces = "application/json")
    public ResponseEntity<ResponseDTO> sendOtp(@PathVariable @NotBlank(message = "{user.identifier.absent}") String identifier)
            throws MediLinkAI, Exception {
        userService.sendOtp(identifier);
        return new ResponseEntity<>(new ResponseDTO("OTP sent successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/verifyOtp/{identifier}/{otp}", produces = "application/json")
    public ResponseEntity<ResponseDTO> verifyOtp(@PathVariable @NotBlank(message = "{user.identifier.absent}") String identifier,
            @PathVariable @Pattern(regexp = "^[0-9]{6}$", message = "{otp.invalid}") String otp) throws MediLinkAI {
        Boolean isValid = userService.verifyOtp(identifier, otp);
        if (isValid) {
            return new ResponseEntity<>(new ResponseDTO("OTP verified successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO("OTP verification failed"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/verifyMfaSecret/{identifier}/{secret}", produces = "application/json")
    public ResponseEntity<ResponseDTO> verifyMfaSecret(
            @PathVariable @NotBlank(message = "{user.identifier.absent}") String identifier,
            @PathVariable @NotBlank(message = "{mfa.secret.absent}") String secret) throws MediLinkAI {
        Boolean isValid = userService.verifyMfaSecret(identifier, secret);
        if (isValid) {
            return new ResponseEntity<>(new ResponseDTO("MFA Secret verified successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO("MFA Secret verification failed"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/changePassword")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody @Valid LoginDTO loginDTO) throws MediLinkAI {
        ResponseDTO responseDTO = userService.changePassword(loginDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
