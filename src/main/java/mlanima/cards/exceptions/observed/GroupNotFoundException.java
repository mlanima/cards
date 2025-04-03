package mlanima.cards.exceptions.observed;

import org.springframework.http.HttpStatus;

public class GroupNotFoundException extends ObservedException{
    public GroupNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Group not found");
    }
}
