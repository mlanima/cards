package mlanima.cards.exceptions.observed;

import org.springframework.http.HttpStatus;

public class DeckNotFoundException extends ObservedException {
    public DeckNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Deck not found");
    }
}
