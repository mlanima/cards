package mlanima.cards.core.deck;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import mlanima.cards.core.card.Card;
import mlanima.cards.core.tag.Tag;
import mlanima.cards.core.user.User;

import java.util.ArrayList;
import java.util.List;

@Table(
        name = "decks"
)
@Entity
@Data
@NoArgsConstructor
public class Deck {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false )
    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn( name = "user_id", nullable = false )
    User user;

    @JoinTable(
            name = "tag_decks",
            joinColumns = @JoinColumn( name = "deck_id"),
            inverseJoinColumns = @JoinColumn( name = "tag_id")
    )
    @ManyToMany
    List<Tag> tags;

    @OneToMany
    private List<Card> cards = new ArrayList<>();

    public Deck(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
