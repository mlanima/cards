package mlanima.cards.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CardDTO {
    @NotBlank
    private String phrase;
    @NotBlank
    private String translation;
}
