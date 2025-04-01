package mlanima.cards.core.card;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mlanima.cards.core.deck.Deck;
import mlanima.cards.core.user.User;
import mlanima.cards.dtos.requests.CardDTO;

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

//    public Card(String phrase, String translation) {}
//
//    public Card build(CardDTO cardDTO) {
//        return new Card(cardDTO.getPhrase(), cardDTO.getTranslation());
//    }
}
