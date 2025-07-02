package mlanima.cards.exceptions.observed;

import org.springframework.http.HttpStatus;

public class ChatJsonConvertingException extends ObservedException {
    public ChatJsonConvertingException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Error by reading Chat Response occurred");
    }
}
