package mlanima.cards.exceptions.observed;

import org.springframework.http.HttpStatus;

public class TagNotFoundException extends ObservedException{
    public TagNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Tag not found");
    }
}
