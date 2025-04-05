package mlanima.cards.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    private String newEmail;
    private String newPassword;
    @NotBlank
    private String password;
}
