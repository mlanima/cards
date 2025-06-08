package mlanima.cards.dtos.responses;

import lombok.Builder;
import lombok.Data;
import mlanima.cards.core.deck.PublicDeck;

@Data
@Builder
public class PublicDeckResponse {
    Long id; //public id
    String name;

    static PublicDeckResponse build(final PublicDeck publicDeck) {
        return PublicDeckResponse.builder()
                .id(publicDeck.getId())
                .name(publicDeck.getDeck().getName())
                .build();
    }
}
