package mlanima.cards.core.user;

import jakarta.validation.Valid;
import mlanima.cards.dtos.requests.UserRequest;
import mlanima.cards.dtos.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserResponse getUser() {
        return UserResponse.build(userService.getUser()) ;
    }

    @PutMapping
    public UserResponse updateUser(@Valid @RequestBody UserRequest user) {
        return UserResponse.build(userService.updateUser(user)) ;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser() {
        userService.deleteUser();
    }
}
