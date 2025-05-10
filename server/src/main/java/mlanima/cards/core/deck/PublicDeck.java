package mlanima.cards.core.deck;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;


@Entity
@Table(name = "public_decks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicDeck {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name="deck_id", unique=true)
    private Deck deck;

    public PublicDeck(Deck deck) {
        this.deck = deck;
    }

    static public PublicDeck fromDeck(Deck deck) {
        return new PublicDeck(
                deck
        );
    }
}
