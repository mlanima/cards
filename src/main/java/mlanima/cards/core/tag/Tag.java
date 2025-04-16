package mlanima.cards.core.tag;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mlanima.cards.core.deck.Deck;
import mlanima.cards.core.user.User;

import java.util.List;

@Entity
@Table( name = "tags", uniqueConstraints = {
        @UniqueConstraint( columnNames = {"user_id", "name"})
})
@Data
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "tag_decks",
            joinColumns = @JoinColumn( name = "tag_id"),
            inverseJoinColumns = @JoinColumn( name = "deck_id")
    )
    List<Deck> decks;

    @ManyToOne
    User user;

    public Tag(String name, User user) {
        this.name = name;
        this.user = user;
    }
}