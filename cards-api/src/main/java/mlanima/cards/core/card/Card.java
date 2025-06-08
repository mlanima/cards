package mlanima.cards.core.card;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import mlanima.cards.core.deck.Deck;

@Table( name = "cards")
@Entity
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false )
    @NotBlank
    String phrase;

    @Column( nullable = false )
    @NotBlank
    String translation;

    @ManyToOne
    @JoinColumn( name = "deck_id", nullable = false )
    Deck deck;

}
