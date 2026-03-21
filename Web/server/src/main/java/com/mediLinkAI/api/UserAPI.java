package com.mediLinkAI.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mediLinkAI.dto.LoginDTO;
import com.mediLinkAI.dto.ResponseDTO;
import com.mediLinkAI.dto.UserDTO;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.services.UserService;

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

    @PostMapping(value = "/sendOtp/{email}", produces = "application/json")
    public ResponseEntity<ResponseDTO> sendOtp(@PathVariable @Email(message = "{user.email.invalid}") String email)
            throws MediLinkAI, Exception {
        userService.sendOtp(email);
        return new ResponseEntity<>(new ResponseDTO("OTP sent successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/verifyOtp/{email}/{otp}", produces = "application/json")
    public ResponseEntity<ResponseDTO> verifyOtp(@PathVariable @Email(message = "{user.email.invalid}") String email,
            @PathVariable @Pattern(regexp = "^[0-9]{6}$", message = "{otp.invalid}") String otp) throws MediLinkAI {
        Boolean isValid = userService.verifyOtp(email, otp);
        if (isValid) {
            return new ResponseEntity<>(new ResponseDTO("OTP verified successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO("OTP verification failed"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/changePassword")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody @Valid LoginDTO loginDTO) throws MediLinkAI {
        ResponseDTO responseDTO = userService.changePassword(loginDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
