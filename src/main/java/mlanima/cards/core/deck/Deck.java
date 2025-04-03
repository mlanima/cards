package mlanima.cards.core.deck;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import mlanima.cards.core.group.Group;
import mlanima.cards.core.user.User;

@Table( name = "decks")
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

    @ManyToOne
    @JoinTable(
            name = "group_decks",
            joinColumns = @JoinColumn( name = "deck_id"),
            inverseJoinColumns = @JoinColumn( name = "shelf_id")
    )
    Group group;

    public Deck(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
