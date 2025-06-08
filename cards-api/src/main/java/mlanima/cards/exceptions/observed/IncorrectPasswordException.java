package mlanima.cards.exceptions.observed;

import org.springframework.http.HttpStatus;

public class IncorrectPasswordException extends ObservedException{
    public IncorrectPasswordException() {
        super(HttpStatus.BAD_REQUEST, "Incorrect password");
    }
}
