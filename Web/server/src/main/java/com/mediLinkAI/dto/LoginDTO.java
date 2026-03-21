package com.mediLinkAI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "{user.email.absent}")
    @Email(message = "{user.email.invalid}")
    private String email;
    @NotBlank(message = "{user.password.absent}")
    private String password;
}
