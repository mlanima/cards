package mlanima.cards.core.group;

import jakarta.persistence.*;
import mlanima.cards.core.deck.Deck;
import mlanima.cards.core.user.User;

import java.util.List;

@Entity
@Table( name = "groups")
public class Group {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    @JoinTable(
            name = "group_decks",
            joinColumns = @JoinColumn( name = "group_id"),
            inverseJoinColumns = @JoinColumn( name = "deck_id")
    )
    List<Deck> decks;

    @ManyToOne
    User user;
}
