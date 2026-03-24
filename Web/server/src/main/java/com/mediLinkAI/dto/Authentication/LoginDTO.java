package com.mediLinkAI.dto.Authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "{user.identifier.absent}")
    private String identifier;
    @NotBlank(message = "{user.password.absent}")
    private String password;
}
