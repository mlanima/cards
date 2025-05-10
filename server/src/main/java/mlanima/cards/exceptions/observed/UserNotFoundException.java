package mlanima.cards.exceptions.observed;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ObservedException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "User not found");
    }
}
