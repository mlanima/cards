package mlanima.cards.exceptions.observed;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends ObservedException{
    public UserAlreadyExistException() {
        super(HttpStatus.CONFLICT, "User already exists");
    }
}
