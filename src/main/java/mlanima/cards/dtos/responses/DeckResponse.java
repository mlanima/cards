package mlanima.cards.dtos.responses;

import lombok.Builder;
import lombok.Data;
import mlanima.cards.core.deck.Deck;

@Data
@Builder
public class DeckResponse {
    private Long id;
    private String name;

    public static DeckResponse build(Deck deck) {
        return DeckResponse.builder()
                .id(deck.getId())
                .name(deck.getName())
                .build();
    }
}
