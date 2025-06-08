package mlanima.cards.dtos.responses;

import lombok.Data;

@Data
public class ExceptionResponse {
    private String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }
}
