package mlanima.cards.exceptions.observed;

import org.springframework.http.HttpStatus;

public class CardNotFoundException extends ObservedException {

    public CardNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Card not found");
    }
}
