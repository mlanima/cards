package mlanima.cards.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    private String newEmail;
    private String newPassword;
    @NotBlank
    private String password;
}
