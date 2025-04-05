package mlanima.cards.dtos.responses;

import lombok.Builder;
import lombok.Data;
import mlanima.cards.core.card.Card;

@Data
@Builder
public class CardResponse {
    Long id;
    String phrase;
    String translation;
    Long deckId;

    public static CardResponse build(Card card) {
        return CardResponse.builder()
                .id(card.getId())
                .phrase(card.getPhrase())
                .translation(card.getTranslation())
                .deckId(card.getDeck().getId())
                .build();
    }
}
