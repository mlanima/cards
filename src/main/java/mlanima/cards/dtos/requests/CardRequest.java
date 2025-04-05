package mlanima.cards.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import mlanima.cards.core.card.Card;

@Data
public class CardRequest {
    @NotBlank
    private String phrase;
    @NotBlank
    private String translation;

    public CardRequest build(Card card) {
        CardRequest cardRequest = new CardRequest();
        cardRequest.setPhrase(card.getPhrase());
        cardRequest.setTranslation(card.getTranslation());
        return cardRequest;
    }
}
