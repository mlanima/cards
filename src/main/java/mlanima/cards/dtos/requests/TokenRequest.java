package mlanima.cards.dtos.requests;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TokenRequest {

    @Email
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

}
