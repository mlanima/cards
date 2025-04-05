package mlanima.cards.core.user;

import jakarta.validation.Valid;
import mlanima.cards.dtos.requests.UserRequest;
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
    public User getUser() {
        return userService.getUser();
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody UserRequest user) {
        return userService.updateUser(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser() {
        userService.deleteUser();
    }
}
