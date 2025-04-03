package mlanima.cards.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AiCardsGenerationRequest {
    @NotBlank
    private String input;
}
