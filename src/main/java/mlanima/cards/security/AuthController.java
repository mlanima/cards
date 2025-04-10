package mlanima.cards.security;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import mlanima.cards.core.user.User;
import mlanima.cards.core.user.UserRepository;
import mlanima.cards.dtos.requests.RegisterRequest;
import mlanima.cards.dtos.requests.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping( "/token" )
    public ResponseEntity<?> login(@Valid @RequestBody TokenRequest tokenRequest) {

        String token = authService.login(tokenRequest);

        return ResponseEntity.ok()
                .header(
                        "Authorization",
                        "Bearer " + token
                )
                .build();
    }

    @GetMapping("/token")
    public ResponseEntity<?> refresh() {

        return ResponseEntity.ok()
                .header(
                        "Authorization",
                        "Bearer " + authService.refreshToken()
                )
                .build();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
}
