package mlanima.cards.exceptions.observed;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ObservedException extends RuntimeException {

    private final HttpStatus status;

    public ObservedException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
