package mlanima.cards.dtos.responses;

import lombok.Builder;
import lombok.Data;
import mlanima.cards.core.card.Card;
import mlanima.cards.core.deck.Deck;

import java.util.List;

@Data
@Builder
public class DeckWithCardsResponse {
    Long id;
    String name;
    List<Card> cards;

    public static DeckWithCardsResponse build(Deck deck) {
        return DeckWithCardsResponse.builder()
                .id(deck.getId())
                .name(deck.getName())
                .cards(deck.getCards())
                .build();
    }
}
