package com.mediLinkAI.dto;

import com.mediLinkAI.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank(message = "{user.name.absent}")
    private String name;
    @NotBlank(message = "{user.email.absent}")
    @Email(message = "{user.email.invalid}")
    private String email;
    @NotBlank(message = "{user.password.absent}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "{user.password.invalid}")
    private String password;

    private AccountType accountType;

    public User toEntity() {
        return new User(id, name, email, password, accountType);
    }
}
