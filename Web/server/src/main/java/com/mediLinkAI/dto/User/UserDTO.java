package com.mediLinkAI.dto.User;

import java.util.UUID;
import com.mediLinkAI.entity.User.User;

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
    private UUID id;
    @NotBlank(message = "{user.name.absent}")
    private String name;
    @NotBlank(message = "{user.email.absent}")
    @Email(message = "{user.email.invalid}")
    private String email;

    @NotBlank(message = "{user.phone.absent}")
    private String phone; // Added phone number field

    private boolean mfaRequired = false; // Flag to indicate if MFA is required upon login

    @NotBlank(message = "{user.password.absent}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "{user.password.invalid}")
    private String password;

    public User toEntity() {
        User user = new User();
        // user.setId(...) -> ID shouldn't be mapped if it's generated, or needs UUID
        // parsing if passed
        user.setEmail(this.email);
        user.setPhoneHash(this.phone); // using plain phone as phoneHash for simplicity as per plan
        user.setPasswordHash(this.password); // Assuming you'll hash this properly in the service layer

        // Note: The User entity doesn't have 'name' or 'accountType'.
        // You will either need to add those to your User entity,
        // or remove them here if they belong to a different entity.

        // You'll also need to set required non-null fields for the User entity
        // that don't have default values (like phoneHash, status). For example:
        // user.setPhoneHash("");
        // user.setStatus(UserStatus.ACTIVE);

        return user;
    }

}
